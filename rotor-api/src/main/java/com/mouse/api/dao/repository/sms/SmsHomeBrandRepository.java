package com.mouse.api.dao.repository.sms;

import com.mouse.api.dao.entity.sms.SmsHomeBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 首页推荐品牌表
 * @Date 2019-11-26
 */
@Repository
public interface SmsHomeBrandRepository extends JpaRepository<SmsHomeBrandEntity, Long>, JpaSpecificationExecutor<SmsHomeBrandEntity> {
}