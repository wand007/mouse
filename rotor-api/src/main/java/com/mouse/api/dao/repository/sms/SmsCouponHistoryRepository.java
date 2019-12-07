package com.mouse.api.dao.repository.sms;

import com.mouse.api.dao.entity.sms.SmsCouponHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 优惠券使用、领取历史表
 * @Date 2019-11-26
 */
@Repository
public interface SmsCouponHistoryRepository extends JpaRepository<SmsCouponHistoryEntity, Long>, JpaSpecificationExecutor<SmsCouponHistoryEntity> {

}