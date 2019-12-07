package com.mouse.api.dao.repository.sms;

import com.mouse.api.dao.entity.sms.SmsFlashPromotionProductRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 商品限时购与商品关系表
 * @Date 2019-11-26
 */
@Repository
public interface SmsFlashPromotionProductRelationRepository extends JpaRepository<SmsFlashPromotionProductRelationEntity, Long>, JpaSpecificationExecutor<SmsFlashPromotionProductRelationEntity> {
}