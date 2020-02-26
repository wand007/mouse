package com.mouse.dao.repository.operate;

import com.mouse.dao.entity.operate.CouponUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface CouponUserRepository extends JpaRepository<CouponUserEntity, Integer>, JpaSpecificationExecutor<CouponUserEntity> {

    /**
     * 根据用户ID查询用户优惠券
     *
     * @param userId  用户ID
     * @param deleted
     * @return
     */
    Optional<List<CouponUserEntity>> findByUserIdAndDeleted(String userId, Boolean deleted);

    /**
     * 查询优惠券领取集合
     *
     * @param deleted
     * @param couponIds
     * @return
     */
    Optional<List<CouponUserEntity>> findByDeletedAndIdIn(boolean deleted, List<Integer> couponIds);

    /**
     * 统计优惠券可领取数量
     *
     * @param couponId 优惠券ID
     * @return
     */
    Integer countByCouponIdAndDeleted(Integer couponId, boolean deleted);

    /**
     * 统计用户优惠券领取数量
     *
     * @param userId   用户ID
     * @param couponId 优惠券ID
     * @return
     */
    Integer countByUserIdAndCouponIdAndDeleted(String userId, Integer couponId, boolean deleted);

    /**
     * 根据用户ID和优惠ID查询优惠券使用记录
     *
     * @param userId   用户ID
     * @param couponId 优惠券记录ID
     * @param deleted
     * @return
     */
    Optional<CouponUserEntity> findByUserIdAndCouponIdAndDeleted(String userId, Integer couponId, boolean deleted);

}