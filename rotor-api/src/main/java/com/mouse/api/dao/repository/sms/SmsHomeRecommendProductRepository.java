package com.mouse.api.dao.repository.sms;

import com.mouse.api.dao.entity.sms.SmsHomeRecommendProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 人气推荐商品表
 * @Date 2019-11-26
 */
@Repository
public interface SmsHomeRecommendProductRepository extends JpaRepository<SmsHomeRecommendProductEntity, Long>, JpaSpecificationExecutor<SmsHomeRecommendProductEntity> {
}