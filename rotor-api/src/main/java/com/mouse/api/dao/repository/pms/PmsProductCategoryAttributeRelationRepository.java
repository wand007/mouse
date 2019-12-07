package com.mouse.api.dao.repository.pms;

import com.mouse.api.dao.entity.pms.PmsProductCategoryAttributeRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 产品的分类和属性的关系表
 * @Date 2019-11-26
 */
@Repository
public interface PmsProductCategoryAttributeRelationRepository extends JpaRepository<PmsProductCategoryAttributeRelationEntity, Long>, JpaSpecificationExecutor<PmsProductCategoryAttributeRelationEntity> {
}