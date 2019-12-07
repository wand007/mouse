package com.mouse.api.dao.repository.pms;

import com.mouse.api.dao.entity.pms.PmsProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 产品分类
 * @Date 2019-11-26
 */
@Repository
public interface PmsProductCategoryRepository extends JpaRepository<PmsProductCategoryEntity, Long>, JpaSpecificationExecutor<PmsProductCategoryEntity> {
}