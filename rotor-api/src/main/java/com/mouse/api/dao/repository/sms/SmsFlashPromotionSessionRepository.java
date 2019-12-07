package com.mouse.api.dao.repository.sms;

import com.mouse.api.dao.entity.sms.SmsFlashPromotionSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 限时购场次表
 * @Date 2019-11-26
 */
@Repository
public interface SmsFlashPromotionSessionRepository extends JpaRepository<SmsFlashPromotionSessionEntity, Long>, JpaSpecificationExecutor<SmsFlashPromotionSessionEntity> {

}