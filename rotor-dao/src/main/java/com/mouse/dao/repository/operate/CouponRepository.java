package com.mouse.dao.repository.operate;

import com.mouse.dao.entity.operate.CouponEntity;
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
public interface CouponRepository extends JpaRepository<CouponEntity, Integer>, JpaSpecificationExecutor<CouponEntity> {

    /**
     * 根据优惠券code查询优惠券信息
     *
     * @param code 优惠券code
     * @return
     */
    Optional<CouponEntity> findByCode(String code);

    /**
     * 查询优惠券集合
     *
     * @param deleted
     * @param couponIds
     * @return
     */
    Optional<List<CouponEntity>> findByDeletedAndIdIn(boolean deleted, List<Integer> couponIds);

}