package com.mouse.api.dao.repository.sms;

import com.mouse.api.dao.entity.sms.SmsHomeNewProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 新鲜好物表
 * @Date 2019-11-26
 */
@Repository
public interface SmsHomeNewProductRepository extends JpaRepository<SmsHomeNewProductEntity, Long>, JpaSpecificationExecutor<SmsHomeNewProductEntity> {
}