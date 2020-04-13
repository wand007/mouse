package com.mouse.api.client.mall;

import com.mouse.api.base.GlobalExceptionHandler;
import com.mouse.api.commons.CartComm;
import com.mouse.api.commons.GoodsComm;
import com.mouse.api.commons.GrouponRulesComm;
import com.mouse.api.commons.req.CartCheckedReq;
import com.mouse.api.commons.req.SaveCartReq;
import com.mouse.api.commons.req.UpdateCartReq;
import com.mouse.api.commons.rsp.CartRsp;
import com.mouse.api.feign.mall.CartFeign;
import com.mouse.api.service.*;
import com.mouse.api.system.SystemConfig;
import com.mouse.core.base.BusinessException;
import com.mouse.core.base.R;
import com.mouse.dao.entity.operate.CouponEntity;
import com.mouse.dao.entity.operate.CouponUserEntity;
import com.mouse.dao.entity.operate.GrouponRulesEntity;
import com.mouse.dao.entity.order.CartEntity;
import com.mouse.dao.entity.resource.GoodsEntity;
import com.mouse.dao.entity.resource.GoodsProductEntity;
import com.mouse.dao.entity.user.AddressEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author ; lidongdong
 * @Description 用户购物车服务 API
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("cart")
public class CartClient extends GlobalExceptionHandler implements CartFeign {

    @Autowired
    CartComm cartComm;
    @Autowired
    GoodsComm goodsComm;
    @Autowired
    GrouponRulesComm grouponRulesComm;
    @Autowired
    CartService cartService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    ProductService productService;
    @Autowired
    AddressService addressService;
    @Autowired
    GrouponRulesService grouponRulesService;
    @Autowired
    CouponUserService couponUserService;

    /**
     * 用户购物车信息
     * <p>
     * 如果系统检查商品已删除或已下架，则系统自动删除。
     * 更好的效果应该是告知用户商品失效，允许用户点击按钮来清除失效商品。
     *
     * @param userId 用户ID
     * @return 用户购物车信息
     */
    @Override
    public R index(@RequestParam(name = "userId") String userId) {
        //商品总数量
        int goodsCount = 0;
        //已选中商品数量
        int checkedGoodsCount = 0;
        //商品总金额
        BigDecimal goodsAmount = BigDecimal.ZERO;
        //已选中商品总金额
        BigDecimal checkedGoodsAmount = BigDecimal.ZERO;
        List<CartRsp> cartList = new ArrayList<>();

        List<CartEntity> cartEntities = cartService.findByUserId(userId).orElseGet(() -> Arrays.asList());
        for (CartEntity cartEntity : cartEntities) {
            //验证购物车商品是否再售 -- 异步
            goodsComm.asyncCheckIsOnSale(cartEntity.getGoodsId(), cartEntity.getId());

            CartRsp cartRsp = new CartRsp();
            cartRsp.setGoodsId(cartEntity.getGoodsId());
            cartRsp.setGoodsName(cartEntity.getGoodsName());
            cartRsp.setGoodsSn(cartEntity.getGoodsSn());
            cartRsp.setId(cartEntity.getId());
            cartRsp.setChecked(cartEntity.getChecked());
            cartRsp.setNumber(cartEntity.getNumber());
            cartRsp.setPicUrl(cartEntity.getPicUrl());
            cartRsp.setPrice(cartEntity.getPrice());
            cartRsp.setProductId(cartEntity.getProductId());
            cartRsp.setSpecifications(cartEntity.getSpecifications());
            cartRsp.setUserId(cartEntity.getUserId());
            cartList.add(cartRsp);

            goodsCount += cartEntity.getNumber();
            goodsAmount = goodsAmount.add(cartEntity.getPrice().multiply(new BigDecimal(cartEntity.getNumber())));
            if (cartEntity.getChecked()) {
                checkedGoodsCount += cartEntity.getNumber();
                checkedGoodsAmount = checkedGoodsAmount.add(cartEntity.getPrice().multiply(new BigDecimal(cartEntity.getNumber())));
            }
        }

        Map<String, Object> cartTotal = new HashMap<>(8);
        cartTotal.put("goodsCount", goodsCount);
        cartTotal.put("goodsAmount", goodsAmount);
        cartTotal.put("checkedGoodsCount", checkedGoodsCount);
        cartTotal.put("checkedGoodsAmount", checkedGoodsAmount);

        Map<String, Object> result = new HashMap<>(8);
        result.put("cartList", cartList);
        result.put("cartTotal", cartTotal);
        return R.success(result);
    }


    /**
     * 加入商品到购物车
     * <p>
     * 如果已经存在购物车货品，则增加数量；
     * 否则添加新的购物车货品项。
     *
     * @param userId 用户ID
     * @param param  购物车商品信息， { goodsId: xxx, productId: xxx, number: xxx }
     * @return 加入购物车操作结果
     */
    @Override
    public R add(@RequestParam(name = "userId") String userId,
                 @RequestBody SaveCartReq param) {
        cartComm.saveOrUpdate(userId, param);

        return R.success(cartService.count(userId));
    }


    /**
     * 立即购买
     * <p>
     * 和add方法的区别在于：
     * 1. 如果购物车内已经存在购物车货品，前者的逻辑是数量添加，这里的逻辑是数量覆盖
     * 2. 添加成功以后，前者的逻辑是返回当前购物车商品数量，这里的逻辑是返回对应购物车项的ID
     *
     * @param userId 用户ID
     * @param param  购物车商品信息， { goodsId: xxx, productId: xxx, number: xxx }
     * @return 立即购买操作结果
     */
    @Override
    public R fastadd(@RequestParam(name = "userId") String userId,
                     @RequestBody SaveCartReq param) {

        return R.success(cartComm.saveOrUpdate(userId, param));
    }


    /**
     * 修改购物车商品货品数量
     *
     * @param userId 用户ID
     * @param param  购物车商品信息， { id: xxx, goodsId: xxx, productId: xxx, number: xxx }
     * @return 修改结果
     */
    @Override
    public R update(@RequestParam(name = "userId") String userId,
                    @RequestBody UpdateCartReq param) {
        //根据商品ID和产品ID判断购物车中是否存在此规格商品
        CartEntity cartEntity = cartService.findByUserIdAndProductId(userId, param.getProductId())
                .orElseThrow(() -> new BusinessException("购物车记录不存在"));
        GoodsEntity goodsEntity = goodsService.findById(cartEntity.getGoodsId())
                .orElseThrow(() -> new BusinessException("商品已下架"));
        if (!goodsEntity.getIsOnSale()) {
            throw new BusinessException("商品已下架");
        }
        GoodsProductEntity productEntity = productService.findById(cartEntity.getProductId())
                .orElseThrow(() -> new BusinessException("商品记录不存在"));

        if (productEntity.getNumber() < param.getNumber()) {
            throw new BusinessException("库存不足");
        }
        int num = cartEntity.getNumber() + param.getNumber();
        cartService.updateNumberById(cartEntity.getId(), num);
        return R.success();
    }

    /**
     * 购物车商品货品勾选状态
     * <p>
     * 如果原来没有勾选，则设置勾选状态；如果商品已经勾选，则设置非勾选状态。
     *
     * @param userId 用户ID
     * @param param  产品ID集合
     * @return
     */
    @Override
    public R checked(@RequestParam(name = "userId") String userId,
                     @RequestBody CartCheckedReq param) {
        cartService.updateChecked(userId, param.getProductIds(), param.getIsChecked());
        return index(userId);
    }


    /**
     * 购物车商品删除
     *
     * @param userId     用户ID
     * @param productIds 购物车商品信息， { productIds: xxx }
     * @return 购物车信息
     */
    @Override
    public R delete(@RequestParam(name = "userId") String userId,
                    @RequestBody List<Integer> productIds) {
        cartService.deleteByUserIdAndProductIdIn(userId, productIds);
        return R.success();
    }


    /**
     * 购物车商品货品数量
     * <p>
     * 如果用户没有登录，则返回空数据。
     *
     * @param userId 用户ID
     * @return 购物车商品货品数量
     */
    @Override
    @GetMapping("count")
    public R count(@RequestParam(name = "userId") String userId) {

        return R.success(cartService.count(userId));
    }


    /**
     * 购物车下单
     *
     * @param userId    用户ID
     * @param cartId    购物车商品ID：
     *                  如果购物车商品ID是空，则下单当前用户所有购物车商品；
     *                  如果购物车商品ID非空，则只下单当前购物车商品。
     * @param addressId 收货地址ID：
     *                  如果收货地址ID是空，则查询当前用户的默认地址。
     * @param couponId  优惠券ID：
     *                  如果优惠券ID是空，则自动选择合适的优惠券。
     * @return 购物车操作结果
     */
    @Override
    @GetMapping("checkout")
    public R checkout(@RequestParam(name = "userId") String userId,
                      @RequestParam(name = "cartId") Integer cartId,
                      @RequestParam(name = "addressId") Integer addressId,
                      @RequestParam(name = "couponId") Integer couponId,
                      @RequestParam(name = "userCouponId") Integer userCouponId,
                      @RequestParam(name = "grouponRulesId") Integer grouponRulesId) {
        AddressEntity checkedAddress = null;
        if (0 != addressId) {
            // 收货地址
            checkedAddress = addressService.findById(addressId).orElseThrow(() -> new BusinessException("请添加收货地址"));
            if (!userId.equals(checkedAddress.getUserId())) {
                return R.error("请选择自己的收货地址");
            }
        }

        // 团购优惠
        GrouponRulesEntity grouponRules = null;
        BigDecimal grouponPrice = BigDecimal.ZERO;
        if (grouponRulesId != 0) {
            grouponRules = grouponRulesService.findById(grouponRulesId).orElseThrow(() -> new BusinessException("团购优惠记录不存在"));
            if (grouponRules != null) {
                grouponPrice = grouponRules.getDiscount();
            }
        }

        // 商品价格
        List<CartEntity> checkedGoodsList = null;
        if (cartId == null || cartId == 0) {
            checkedGoodsList = cartService.findByUserIdAndIsChecked(userId, true).orElseGet(() -> new ArrayList<>());
        } else {
            CartEntity cart = cartService.findById(cartId).orElseGet(() -> new CartEntity());
            if (!userId.equals(cart.getUserId())) {
                return R.error("请选择自己购物车里的商品记录");
            }
            checkedGoodsList = Arrays.asList(cart);
        }
        BigDecimal checkedGoodsPrice = BigDecimal.ZERO;
        for (CartEntity cart : checkedGoodsList) {
            //  只有当团购规格商品ID符合才进行团购优惠
            if (grouponRules != null && grouponRules.getGoodsId().equals(cart.getGoodsId())) {
                checkedGoodsPrice = checkedGoodsPrice.add(cart.getPrice().subtract(grouponPrice).multiply(new BigDecimal(cart.getNumber())));
            } else {
                checkedGoodsPrice = checkedGoodsPrice.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
            }
        }

        // 计算优惠券可用情况
        BigDecimal tmpCouponPrice = new BigDecimal(0.00);
        Integer tmpCouponId = 0;
        Integer tmpUserCouponId = 0;
        int tmpCouponLength = 0;
        List<CouponUserEntity> couponUserList = couponUserService.findByUserId(userId).orElseGet(() -> new ArrayList<>());
        for (CouponUserEntity couponUser : couponUserList) {
            tmpUserCouponId = couponUser.getId();
            CouponEntity coupon = grouponRulesComm.checkCoupon(userId, couponUser.getCouponId(), tmpUserCouponId, checkedGoodsPrice);
            if (coupon == null) {
                continue;
            }

            tmpCouponLength++;
            if (tmpCouponPrice.compareTo(coupon.getDiscount()) == -1) {
                tmpCouponPrice = coupon.getDiscount();
                tmpCouponId = coupon.getId();
            }
        }
        // 获取优惠券减免金额，优惠券可用数量
        int availableCouponLength = tmpCouponLength;
        BigDecimal couponPrice = new BigDecimal(0);
        // 这里存在三种情况
        // 1. 用户不想使用优惠券，则不处理
        // 2. 用户想自动使用优惠券，则选择合适优惠券
        // 3. 用户已选择优惠券，则测试优惠券是否合适
        if (couponId == null || couponId.equals(-1)) {
            couponId = -1;
            userCouponId = -1;
        } else if (couponId.equals(0)) {
            couponPrice = tmpCouponPrice;
            couponId = tmpCouponId;
            userCouponId = tmpUserCouponId;
        } else {
            CouponEntity coupon = grouponRulesComm.checkCoupon(userId, couponId, userCouponId, checkedGoodsPrice);
            // 用户选择的优惠券有问题，则选择合适优惠券，否则使用用户选择的优惠券
            if (coupon == null) {
                couponPrice = tmpCouponPrice;
                couponId = tmpCouponId;
                userCouponId = tmpUserCouponId;
            } else {
                couponPrice = coupon.getDiscount();
            }
        }

        // 根据订单商品总价计算运费，满88则免运费，否则8元；
        BigDecimal freightPrice = new BigDecimal(0.00);
        if (checkedGoodsPrice.compareTo(SystemConfig.getFreightLimit()) < 0) {
            freightPrice = SystemConfig.getFreight();
        }

        // 可以使用的其他钱，例如用户积分
        BigDecimal integralPrice = new BigDecimal(0.00);
        // 订单费用
        BigDecimal orderTotalPrice = checkedGoodsPrice.add(freightPrice).subtract(couponPrice).max(new BigDecimal(0.00));

        BigDecimal actualPrice = orderTotalPrice.subtract(integralPrice);

        Map<String, Object> data = new HashMap<>();
        data.put("addressId", addressId);
        data.put("couponId", couponId);
        data.put("userCouponId", userCouponId);
        data.put("cartId", cartId);
        data.put("grouponRulesId", grouponRulesId);
        data.put("grouponPrice", grouponPrice);
        data.put("checkedAddress", checkedAddress);
        data.put("availableCouponLength", availableCouponLength);
        data.put("goodsTotalPrice", checkedGoodsPrice);
        data.put("freightPrice", freightPrice);
        data.put("couponPrice", couponPrice);
        data.put("orderTotalPrice", orderTotalPrice);
        data.put("actualPrice", actualPrice);
        data.put("checkedGoodsList", checkedGoodsList);
        return R.success(data);
    }
}
