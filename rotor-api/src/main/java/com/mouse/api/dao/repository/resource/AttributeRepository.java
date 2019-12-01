package com.mouse.api.dao.repository.resource;

import com.mouse.api.dao.entity.resource.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 属性
 * @Date 2019-11-26
 */
@Repository
public interface AttributeRepository extends JpaRepository<AttributeEntity, Integer>, JpaSpecificationExecutor<AttributeEntity> {
}
