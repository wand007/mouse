package com.mouse.api.dao.repository.sms;

import com.mouse.api.dao.entity.sms.SmsCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 优惠卷表
 * @Date 2019-11-26
 */
@Repository
public interface SmsCouponRepository extends JpaRepository<SmsCouponEntity, Long>, JpaSpecificationExecutor<SmsCouponEntity> {

}