package com.mouse.api.commons;

import com.mouse.api.commons.req.SaveOrderReq;
import com.mouse.api.service.*;
import com.mouse.api.system.SystemConfig;
import com.mouse.core.base.BusinessException;
import com.mouse.core.enums.GrouponRuleStatusEnum;
import com.mouse.core.express.ExpressService;
import com.mouse.core.params.dto.OrderPriceDTO;
import com.mouse.dao.entity.operate.CouponEntity;
import com.mouse.dao.entity.operate.GrouponEntity;
import com.mouse.dao.entity.operate.GrouponRulesEntity;
import com.mouse.dao.entity.order.CartEntity;
import com.mouse.dao.entity.resource.GoodsProductEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2020-02-03
 */
@Slf4j
@Component
public class OrderComm {

    @Autowired
    CartService cartService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsAttributeService goodsAttributeService;
    @Autowired
    GoodsSpecificationService goodsSpecificationService;
    @Autowired
    ProductService productService;
    @Autowired
    GoodsIssueService goodsIssueService;
    @Autowired
    CommentService commentService;
    @Autowired
    GrouponService grouponService;
    @Autowired
    CouponService couponService;
    @Autowired
    CouponUserService couponUserService;

    @Autowired
    GrouponRulesService grouponRulesService;
    @Autowired
    CollectService collectService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderGoodsService orderGoodsService;
    @Autowired
    ExpressService expressService;
    @Autowired
    AddressService addressService;

    /**
     * 校验团购规则参数
     *
     * @param param
     */
    public void checkGrouponRule(SaveOrderReq param) {

        String userId = param.getUserId();
        Integer grouponRulesId = param.getGrouponRulesId();
        Integer grouponLinkId = param.getGrouponLinkId();

        //如果是团购项目,验证活动是否有效
        if (grouponRulesId != null && grouponRulesId > 0) {
            //找不到记录
            GrouponRulesEntity grouponRulesEntity = grouponRulesService.findById(grouponRulesId).orElseThrow(() -> new BusinessException("参数异常"));
            GrouponRuleStatusEnum grouponRuleStatusEnum = GrouponRuleStatusEnum.parse(grouponRulesEntity.getStatus());
            switch (grouponRuleStatusEnum) {
                //团购规则已经过期
                case DOWN_EXPIRE: {
                    throw new BusinessException("团购已过期!");
                }
                //团购规则已经下线
                case DOWN_ADMIN: {
                    throw new BusinessException("团购已下线!");
                }
            }

            if (grouponLinkId != null && grouponLinkId > 0) {
                //团购人数已满
                if (grouponService.countByGrouponId(grouponLinkId) >= (grouponRulesEntity.getDiscountMember() - 1)) {
                    throw new BusinessException("团购活动人数已满!");
                }
                // NOTE
                // 这里业务方面允许用户多次开团，以及多次参团，
                // 但是会限制以下两点：
                // （1）不允许参加已经加入的团购
                if (0 != grouponService.countByUserIdAndGrouponId(userId, grouponLinkId)) {
                    throw new BusinessException("团购活动已经参加!");
                }
                // （2）不允许参加自己开团的团购
                GrouponEntity grouponEntity = grouponService.findById(grouponLinkId).orElseThrow(() -> new BusinessException("团购记录不存在"));
                if (grouponEntity.getCreatorUserId().equals(userId)) {
                    throw new BusinessException("团购活动已经参加!");
                }
            }
        }

    }

    /**
     * 校验商品属性
     *
     * @param param
     */
    public void checkGoodsProductRule(SaveOrderReq param) {
        Integer cartId = param.getCartId();
        String userId = param.getUserId();
        List<CartEntity> checkedGoodsList = null;
        if (cartId == null || cartId.equals(0)) {
            checkedGoodsList = cartService.findByUserIdAndIsChecked(userId, true).orElseGet(() -> new ArrayList<>());
        } else {
            Optional<CartEntity> cartEntityOptional = cartService.findById(cartId);
            if (cartEntityOptional.isPresent()) {
                checkedGoodsList = Arrays.asList(cartEntityOptional.get());
            }
        }
        if (CollectionUtils.isEmpty(checkedGoodsList)) {
            throw new BusinessException("购物车参数值异常");
        }
        // 商品货品数量减少
        for (CartEntity cartEntity : checkedGoodsList) {
            Integer productId = cartEntity.getProductId();
            GoodsProductEntity product = productService.findById(productId).orElseGet(() -> new GoodsProductEntity());

            int remainNumber = product.getNumber() - cartEntity.getNumber();
            if (remainNumber < 0) {
                throw new BusinessException("下单的商品货品数量大于库存量");
            }
//            if (productService.reduceStock(productId, cartEntity.getNumber()) == 0) {
//                throw new BusinessException("商品货品库存减少失败");
//            }
        }
        param.setCartIds(checkedGoodsList.stream().map(CartEntity::getId).collect(Collectors.toList()));
    }

    /**
     * 计算订单金额
     *
     * @param param
     * @return
     */
    public OrderPriceDTO calculationPrice(SaveOrderReq param) {
        OrderPriceDTO orderPriceDTO = new OrderPriceDTO();

        // 团购优惠
        Integer grouponRulesId = param.getGrouponRulesId();
        BigDecimal grouponPrice = BigDecimal.ZERO;
        GrouponRulesEntity grouponRulesEntity = null;
        Optional<GrouponRulesEntity> grouponRulesEntityOptional = grouponRulesService.findById(grouponRulesId);
        if (grouponRulesEntityOptional.isPresent()) {
            grouponRulesEntity = grouponRulesEntityOptional.get();
            grouponPrice = grouponRulesEntity.getDiscount();
        }
        orderPriceDTO.setGrouponPrice(grouponPrice);


        String userId = param.getUserId();

        List<CartEntity> checkedGoodsList = cartService.findByIdIn(param.getCartIds()).orElseGet(() -> new ArrayList<>());
        BigDecimal checkedGoodsPrice = BigDecimal.ZERO;
        for (CartEntity checkGoods : checkedGoodsList) {
            //  只有当团购规格商品ID符合才进行团购优惠
            if (grouponRulesEntity != null && grouponRulesEntity.getGoodsId().equals(checkGoods.getGoodsId())) {
                checkedGoodsPrice = checkedGoodsPrice.add(checkGoods.getPrice().subtract(grouponPrice).multiply(new BigDecimal(checkGoods.getNumber())));
            } else {
                checkedGoodsPrice = checkedGoodsPrice.add(checkGoods.getPrice().multiply(new BigDecimal(checkGoods.getNumber())));
            }
        }

        orderPriceDTO.setCheckedGoodsPrice(checkedGoodsPrice);

        Integer couponId = param.getCouponId();
        Integer userCouponId = param.getUserCouponId();
        // 使用优惠券减免的金额
        BigDecimal couponPrice = BigDecimal.ZERO;
        // 如果couponId=0则没有优惠券，couponId=-1则不使用优惠券
        if (couponId != 0 && couponId != -1) {
            CouponEntity coupon = couponService.checkCoupon(userId, couponId, userCouponId, checkedGoodsPrice);
            couponPrice = coupon.getDiscount();
        }
        orderPriceDTO.setCouponPrice(couponPrice);

        // 根据订单商品总价计算运费，满足条件（例如88元）则免运费，否则需要支付运费（例如8元）；
        BigDecimal freightPrice = BigDecimal.ZERO;
        if (checkedGoodsPrice.compareTo(SystemConfig.getFreightLimit()) < 0) {
            freightPrice = SystemConfig.getFreight();
        }
        orderPriceDTO.setFreightPrice(freightPrice);
        // 可以使用的其他钱，例如用户积分
        BigDecimal integralPrice = BigDecimal.ZERO;

        // 订单费用
        BigDecimal orderTotalPrice = checkedGoodsPrice.add(freightPrice).subtract(couponPrice).max(new BigDecimal(0));
        // 最终支付费用
        BigDecimal actualPrice = orderTotalPrice.subtract(integralPrice);
        orderPriceDTO.setOrderTotalPrice(orderTotalPrice);
        orderPriceDTO.setActualPrice(actualPrice);
        return orderPriceDTO;
    }

}
