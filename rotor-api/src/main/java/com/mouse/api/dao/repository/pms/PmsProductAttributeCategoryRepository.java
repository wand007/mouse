package com.mouse.api.dao.repository.pms;

import com.mouse.api.dao.entity.pms.PmsProductAttributeCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 产品属性分类表
 * @Date 2019-11-26
 */
@Repository
public interface PmsProductAttributeCategoryRepository extends JpaRepository<PmsProductAttributeCategoryEntity, Long>, JpaSpecificationExecutor<PmsProductAttributeCategoryEntity> {

}