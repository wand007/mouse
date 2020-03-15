package com.mouse.api.commons;

import com.mouse.api.commons.rsp.GrouponRuleRsp;
import com.mouse.api.service.CouponService;
import com.mouse.api.service.CouponUserService;
import com.mouse.api.service.GoodsService;
import com.mouse.api.service.GrouponRulesService;
import com.mouse.core.enums.CouponConstant;
import com.mouse.core.enums.CouponTimeTypeEnum;
import com.mouse.core.utils.PageNation;
import com.mouse.dao.entity.operate.CouponEntity;
import com.mouse.dao.entity.operate.CouponUserEntity;
import com.mouse.dao.entity.operate.GrouponRulesEntity;
import com.mouse.dao.entity.resource.GoodsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-22
 */
@Slf4j
@Component
public class GrouponRulesComm {
    @Autowired
    GoodsService goodsService;
    @Autowired
    GrouponRulesService grouponRulesService;
    @Autowired
    CouponService couponService;
    @Autowired
    CouponUserService couponUserService;

    public PageNation<GrouponRulesEntity> findPage(Integer pageNum, Integer pageSize) {
        Page<GrouponRulesEntity> page = grouponRulesService.findPage(pageNum, pageSize);
        List<GrouponRulesEntity> content = page.getContent();
        List<GrouponRuleRsp> result = new ArrayList<>(content.size());
        if (!CollectionUtils.isEmpty(content)) {
            for (GrouponRulesEntity rule : content) {
                Integer goodsId = rule.getGoodsId();
                Optional<GoodsEntity> goodsEntityOptional = goodsService.findById(goodsId);
                if (!goodsEntityOptional.isPresent()) {
                    continue;
                }
                GoodsEntity goodsEntity = goodsEntityOptional.get();
                GrouponRuleRsp grouponRuleVo = new GrouponRuleRsp();
                grouponRuleVo.setId(goodsEntity.getId());
                grouponRuleVo.setName(goodsEntity.getName());
                grouponRuleVo.setBrief(goodsEntity.getBrief());
                grouponRuleVo.setPicUrl(goodsEntity.getPicUrl());
                grouponRuleVo.setCounterPrice(goodsEntity.getCounterPrice());
                grouponRuleVo.setRetailPrice(goodsEntity.getRetailPrice());
                grouponRuleVo.setGrouponPrice(goodsEntity.getRetailPrice().subtract(rule.getDiscount()));
                grouponRuleVo.setGrouponDiscount(rule.getDiscount());
                grouponRuleVo.setGrouponMember(rule.getDiscountMember());
                grouponRuleVo.setExpireTime(rule.getExpireTime());
                result.add(grouponRuleVo);
            }
        }
        return PageNation.of(page, result);
    }

    /**
     * 检测优惠券是否适合
     *
     * @param userId
     * @param couponId
     * @param checkedGoodsPrice
     * @return
     */
    public CouponEntity checkCoupon(String userId, Integer couponId, Integer userCouponId, BigDecimal checkedGoodsPrice) {
        Optional<CouponEntity> couponEntityOptional = couponService.findById(couponId);
        if (!couponEntityOptional.isPresent()) {
            return null;
        }
        CouponEntity coupon = couponEntityOptional.get();
        CouponUserEntity couponUser = null;
        Optional<CouponUserEntity> couponUserEntityOptional = couponUserService.findById(userCouponId);
        if (couponUserEntityOptional.isPresent()) {
            couponUser = couponUserEntityOptional.get();
        } else {
            couponUser = couponUserService.findByCouponIdAndUserId(userId, couponId).orElseGet(() -> new CouponUserEntity());
        }
        if (couponUser == null || !couponId.equals(couponUser.getCouponId())) {
            return null;
        }

        // 检查是否超期
        Short timeType = coupon.getTimeType();
        Short days = coupon.getDays();
        LocalDateTime now = LocalDateTime.now();
        if (timeType.equals(CouponTimeTypeEnum.TIME_TYPE_TIME)) {
            if (now.isBefore(coupon.getStartTime()) || now.isAfter(coupon.getEndTime())) {
                return null;
            }
        } else if (timeType.equals(CouponTimeTypeEnum.TIME_TYPE_DAYS)) {
            LocalDateTime expired = couponUser.getAddTime().plusDays(days);
            if (now.isAfter(expired)) {
                return null;
            }
        } else {
            return null;
        }

        // 检测商品是否符合
        // TODO 目前仅支持全平台商品，所以不需要检测
        Short goodType = coupon.getGoodsType();
        if (!goodType.equals(CouponConstant.GOODS_TYPE_ALL)) {
            return null;
        }

        // 检测订单状态
        Short status = coupon.getStatus();
        if (!status.equals(CouponConstant.STATUS_NORMAL)) {
            return null;
        }
        // 检测是否满足最低消费
        if (checkedGoodsPrice.compareTo(coupon.getMin()) == -1) {
            return null;
        }

        return coupon;
    }
}
