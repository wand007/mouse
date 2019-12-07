package com.mouse.api.dao.repository.sms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 优惠券和产品分类关系表
 * @Date 2019-11-26
 */
@Repository
public interface SmsCouponProductCategoryRelationEntity extends JpaRepository<SmsCouponProductCategoryRelationEntity, Long>, JpaSpecificationExecutor<SmsCouponProductCategoryRelationEntity> {
}