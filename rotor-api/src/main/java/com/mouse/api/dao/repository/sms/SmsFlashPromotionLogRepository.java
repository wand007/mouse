package com.mouse.api.dao.repository.sms;

import com.mouse.api.dao.entity.sms.SmsFlashPromotionLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 限时购通知记录
 * @Date 2019-11-26
 */
@Repository
public interface SmsFlashPromotionLogRepository extends JpaRepository<SmsFlashPromotionLogEntity, Long>, JpaSpecificationExecutor<SmsFlashPromotionLogEntity> {

}