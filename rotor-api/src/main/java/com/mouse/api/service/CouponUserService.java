package com.mouse.api.service;

import com.mouse.dao.entity.operate.CouponUserEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2020-01-12
 */
public interface CouponUserService {


    Optional<CouponUserEntity> findById(Integer id);

    Optional<List<CouponUserEntity>> findByUserId(String userId);

    Page<CouponUserEntity> findPage(String userId, Integer status, Integer pageNum, Integer pageSize);


    /**
     * 统计优惠券可领取数量
     *
     * @param couponId 优惠券ID
     * @return
     */
    Integer countByCouponId(Integer couponId);

    /**
     * 统计用户优惠券领取数量
     *
     * @param userId   用户ID
     * @param couponId 优惠券ID
     * @return
     */
    Integer countByUserIdAndCouponId(String userId, Integer couponId);

    /**
     * 保存用户优惠券领取记录
     *
     * @param userId   用户ID
     * @param couponId 优惠券ID
     */
    void save(String userId, Integer couponId);


    void update(CouponUserEntity couponUser);

    Optional<CouponUserEntity> findByCouponIdAndUserId(String userId, Integer couponId);
}
