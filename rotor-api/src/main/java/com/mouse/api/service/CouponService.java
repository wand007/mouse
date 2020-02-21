package com.mouse.api.service;

import com.mouse.dao.entity.operate.CouponEntity;
import com.mouse.dao.entity.operate.CouponUserEntity;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-15
 */
public interface CouponService {

    Optional<CouponEntity> findById(Integer couponId);

    Page<CouponEntity> findPage(Integer pageNum, Integer pageSize);

    Page<CouponEntity> findByUserIdPage(Integer userId, Integer pageNum, Integer pageSize);

    Optional<List<CouponEntity>> findByIdIn(List<Integer> couponIds);

    Optional<CouponEntity> findByCode(String code);

    /**
     * 检测优惠券是否适合
     *
     * @param userId
     * @param couponId
     * @param checkedGoodsPrice
     * @return
     */
     CouponEntity checkCoupon(Integer userId, Integer couponId, Integer userCouponId, BigDecimal checkedGoodsPrice);
}
