package com.mouse.api.dao.repository.operate;

import com.mouse.api.dao.entity.operate.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 优惠券
 * @Date 2019-11-26
 */
@Repository
public interface CouponRepository extends JpaRepository<CouponEntity, Integer>, JpaSpecificationExecutor<CouponEntity> {
}
