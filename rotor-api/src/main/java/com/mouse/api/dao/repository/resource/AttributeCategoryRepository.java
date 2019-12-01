package com.mouse.api.dao.repository.resource;

import com.mouse.api.dao.entity.resource.AttributeCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 属性类目
 * @Date 2019-11-26
 */
@Repository
public interface AttributeCategoryRepository extends JpaRepository<AttributeCategoryEntity, Integer>, JpaSpecificationExecutor<AttributeCategoryEntity> {
}
