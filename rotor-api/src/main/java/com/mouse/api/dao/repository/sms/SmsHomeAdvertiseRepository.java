package com.mouse.api.dao.repository.sms;

import com.mouse.api.dao.entity.sms.SmsHomeAdvertiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 首页轮播广告表
 * @Date 2019-11-26
 */
@Repository
public interface SmsHomeAdvertiseRepository extends JpaRepository<SmsHomeAdvertiseEntity, Long>, JpaSpecificationExecutor<SmsHomeAdvertiseEntity> {

}