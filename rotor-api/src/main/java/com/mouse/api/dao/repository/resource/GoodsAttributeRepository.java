package com.mouse.api.dao.repository.resource;

import com.mouse.api.dao.entity.resource.GoodsAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 商品属性
 * @Date 2019-11-26
 */
@Repository
public interface GoodsAttributeRepository extends JpaRepository<GoodsAttributeEntity, Integer>, JpaSpecificationExecutor<GoodsAttributeEntity> {
}
