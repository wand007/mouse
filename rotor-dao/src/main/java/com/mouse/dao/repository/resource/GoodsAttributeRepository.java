package com.mouse.dao.repository.resource;

import com.mouse.dao.entity.resource.GoodsAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface GoodsAttributeRepository extends JpaRepository<GoodsAttributeEntity, Integer>, JpaSpecificationExecutor<GoodsAttributeEntity> {

}