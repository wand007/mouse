package com.mouse.api.client;

import com.mouse.api.base.BaseClient;
import com.mouse.api.commons.GrouponRulesComm;
import com.mouse.api.commons.rsp.CouponRsp;
import com.mouse.api.commons.rsp.CouponUserRsp;
import com.mouse.api.feign.CouponFeign;
import com.mouse.api.service.*;
import com.mouse.core.base.BusinessException;
import com.mouse.core.base.R;
import com.mouse.core.enums.CouponConstant;
import com.mouse.core.utils.PageNation;
import com.mouse.dao.entity.operate.CouponEntity;
import com.mouse.dao.entity.operate.CouponUserEntity;
import com.mouse.dao.entity.operate.GrouponRulesEntity;
import com.mouse.dao.entity.order.CartEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mouse.core.enums.CouponConstant.*;

/**
 * @author ; lidongdong
 * @Description 优惠券服务 API
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("coupon")
public class CouponClient extends BaseClient implements CouponFeign {

    @Autowired
    GrouponRulesComm grouponRulesComm;
    @Autowired
    CouponService couponService;
    @Autowired
    GrouponRulesService grouponRulesService;
    @Autowired
    GrouponService grouponService;
    @Autowired
    CartService cartService;
    @Autowired
    CouponUserService couponUserService;
    @Autowired
    GoodsService goodsService;

    /**
     * 优惠券列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public R findPage(@Min(value = 0, message = "必须从0页开始")
                      @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                      @Min(value = 1, message = "每页必须大于1")
                      @Max(value = 300, message = "每页必须小于300")
                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {

        Page<CouponEntity> page = couponService.findPage(pageNum, pageSize);
        List<CouponEntity> content = page.getContent();
        List<CouponRsp> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(content)) {
            content.stream().forEach(couponEntity -> {
                CouponRsp couponRsp = new CouponRsp();
                couponRsp.setId(couponEntity.getId());
                couponRsp.setName(couponEntity.getName());
                couponRsp.setCode(couponEntity.getCode());
                couponRsp.setType(couponEntity.getType());
                couponRsp.setDays(couponEntity.getDays());
                couponRsp.setDesc(couponEntity.getDesc());
                couponRsp.setDiscount(couponEntity.getDiscount());
                couponRsp.setGoodsType(couponEntity.getGoodsType());
                couponRsp.setGoodsValue(couponEntity.getGoodsValue());
                couponRsp.setLimit(couponEntity.getLimit());
                couponRsp.setMin(couponEntity.getMin());
                couponRsp.setStartTime(couponEntity.getStartTime());
                couponRsp.setEndTime(couponEntity.getEndTime());
                couponRsp.setAddTime(couponEntity.getAddTime());
                couponRsp.setStatus(couponEntity.getStatus());
                couponRsp.setTimeType(couponEntity.getTimeType());
                couponRsp.setTag(couponEntity.getTag());
                couponRsp.setTotal(couponEntity.getTotal());
                result.add(couponRsp);
            });
        }
        return R.success(PageNation.of(page, result));
    }

    /**
     * 个人优惠券列表
     *
     * @param userId
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public R findPersonalPage(@RequestParam(name = "userId") Integer userId,
                              @RequestParam(name = "status") Integer status,
                              @Min(value = 0, message = "必须从0页开始")
                              @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                              @Min(value = 1, message = "每页必须大于1")
                              @Max(value = 300, message = "每页必须小于300")
                              @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        Page<CouponUserEntity> page = couponUserService.findPage(userId, status, pageNum, pageSize);

        return R.success(PageNation.of(page, toResult(page.getContent())));
    }


    private List<CouponUserRsp> toResult(List<CouponUserEntity> content) {
        List<CouponUserRsp> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(content)) {
            List<Integer> couponIds = content.stream().map(CouponUserEntity::getCouponId).collect(Collectors.toList());
            List<CouponEntity> couponEntities = couponService.findByIdIn(couponIds).orElseGet(() -> new ArrayList<>());
            Map<Integer, CouponEntity> couponMap = couponEntities.stream().collect(Collectors.toMap(CouponEntity::getId, a -> a, (k1, k2) -> k1));

            content.stream().forEach(couponUserEntity -> {
                CouponUserRsp couponRsp = new CouponUserRsp();
                couponRsp.setId(couponUserEntity.getId());
                couponRsp.setStartTime(couponUserEntity.getStartTime());
                couponRsp.setEndTime(couponUserEntity.getEndTime());
                CouponEntity couponEntity = couponMap.get(couponUserEntity.getCouponId());
                boolean availableb = couponEntity != null;
                couponRsp.setAvailable(availableb);
                if (availableb) {
                    couponRsp.setCid(couponEntity.getId());
                    couponRsp.setName(couponEntity.getName());
                    couponRsp.setDesc(couponEntity.getDesc());
                    couponRsp.setTag(couponEntity.getTag());
                    couponRsp.setMin(couponEntity.getMin());
                    couponRsp.setDiscount(couponEntity.getDiscount());
                }
                result.add(couponRsp);
            });
        }
        return result;
    }

    /**
     * 当前购物车下单商品订单可用优惠券
     *
     * @param userId
     * @param cartId
     * @param grouponRulesId
     * @return
     */
    @Override
    public R availableList(@RequestParam(name = "userId") Integer userId,
                           @RequestParam(name = "cartId") Integer cartId,
                           @RequestParam(name = "grouponRulesId") Integer grouponRulesId) {

        GrouponRulesEntity grouponRulesEntity = grouponRulesService.findById(grouponRulesId).orElseGet(() -> {
            GrouponRulesEntity entity = new GrouponRulesEntity();
            entity.setDiscount(BigDecimal.ZERO);
            return entity;
        });

        // 团购优惠
        BigDecimal grouponPrice = grouponRulesEntity.getDiscount();

        // 商品价格
        List<CartEntity> cartEntities = new ArrayList<>(1);
        if (cartId == null || cartId.equals(0)) {
            cartEntities = cartService.findByUserIdAndIsChecked(userId, true).orElseGet(() -> new ArrayList<>());
        } else {
            CartEntity cartEntity = cartService.findById(cartId).orElseThrow(() -> new BusinessException("购物车记录不存在"));
            cartEntities.add(cartEntity);
        }
        BigDecimal checkedGoodsPrice = BigDecimal.ZERO;
        for (CartEntity cartEntity : cartEntities) {
            //  只有当团购规格商品ID符合才进行团购优惠
            if (grouponRulesEntity != null && grouponRulesEntity.getGoodsId().equals(cartEntity.getGoodsId())) {
                checkedGoodsPrice = checkedGoodsPrice.add(cartEntity.getPrice().subtract(grouponPrice).multiply(new BigDecimal(cartEntity.getNumber())));
            } else {
                checkedGoodsPrice = checkedGoodsPrice.add(cartEntity.getPrice().multiply(new BigDecimal(cartEntity.getNumber())));
            }
        }

        // 计算优惠券可用情况
        List<CouponUserEntity> couponUserEntities = couponUserService.findByUserId(userId).orElseGet(() -> new ArrayList<>());
        return R.success(this.toResult(couponUserEntities));
    }


    /**
     * 优惠券领取
     *
     * @param userId   用户ID
     * @param couponId 优惠券ID
     * @return 操作结果
     */
    @Override
    public R receive(@RequestParam(name = "userId") Integer userId,
                     @RequestParam(name = "couponId") Integer couponId) {

        CouponEntity couponEntity = couponService.findById(couponId).orElseThrow(() -> new BusinessException("优惠券记录不存在"));

        // 当前已领取数量和总数量比较
        Integer total = couponEntity.getTotal();
        Integer totalCoupons = couponUserService.countByCouponId(couponId);
        if ((total != 0) && (totalCoupons >= total)) {
            return R.error("优惠券已领完");
        }

        // 当前用户已领取数量和用户限领数量比较
        Integer limit = couponEntity.getLimit().intValue();
        Integer userCounpons = couponUserService.countByUserIdAndCouponId(userId, couponId);
        if ((limit != 0) && (userCounpons >= limit)) {
            return R.error("优惠券已经领取过");
        }

        // 优惠券分发类型
        // 例如注册赠券类型的优惠券不能领取
        Short type = couponEntity.getType();
        if (type.equals(TYPE_REGISTER)) {
            return R.error("新用户优惠券自动发送");
        } else if (type.equals(TYPE_CODE)) {
            return R.error("优惠券只能兑换");
        } else if (!type.equals(TYPE_COMMON)) {
            return R.error("优惠券类型不支持");
        }

        // 优惠券状态，已下架或者过期不能领取
        Short status = couponEntity.getStatus();
        if (status.equals(STATUS_OUT)) {
            return R.error("优惠券已领完");
        } else if (status.equals(STATUS_EXPIRED)) {
            return R.error("优惠券已经过期");
        }

        // 用户领券记录
        couponUserService.save(userId, couponId);

        return R.success();
    }


    /**
     * 优惠券兑换
     *
     * @param userId 用户ID
     * @param code   优惠券code
     * @return 操作结果
     */
    @Override
    @PostMapping("exchange")
    public R exchange(@RequestParam(name = "userId") Integer userId,
                      @RequestParam(name = "code") String code) {

        CouponEntity coupon = couponService.findByCode(code).orElseThrow(() -> new BusinessException("优惠券不正确"));
        Integer couponId = coupon.getId();

        // 当前已领取数量和总数量比较
        Integer total = coupon.getTotal();
        Integer totalCoupons = couponUserService.countByCouponId(couponId);
        if ((total != 0) && (totalCoupons >= total)) {
            return R.error("优惠券已兑换");
        }

        // 当前用户已领取数量和用户限领数量比较
        Integer limit = coupon.getLimit().intValue();
        Integer userCounpons = couponUserService.countByUserIdAndCouponId(userId, couponId);
        if ((limit != 0) && (userCounpons >= limit)) {
            return R.error("优惠券已兑换");
        }

        // 优惠券分发类型
        // 例如注册赠券类型的优惠券不能领取
        Short type = coupon.getType();
        if (type.equals(CouponConstant.TYPE_REGISTER)) {
            return R.error("新用户优惠券自动发送");
        } else if (type.equals(CouponConstant.TYPE_COMMON)) {
            return R.error("优惠券只能领取，不能兑换");
        } else if (!type.equals(CouponConstant.TYPE_CODE)) {
            return R.error("优惠券类型不支持");
        }

        // 优惠券状态，已下架或者过期不能领取
        Short status = coupon.getStatus();
        if (status.equals(CouponConstant.STATUS_OUT)) {
            return R.error("优惠券已兑换");
        } else if (status.equals(CouponConstant.STATUS_EXPIRED)) {
            return R.error("优惠券已经过期");
        }

        // 用户领券记录
        couponUserService.save(userId, couponId);
        return R.success();
    }
}
