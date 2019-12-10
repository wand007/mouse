package com.mouse.dao.repository.resource;

import com.mouse.dao.entity.resource.GoodsSpecificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface GoodsSpecificationRepository extends JpaRepository<GoodsSpecificationEntity, Integer>, JpaSpecificationExecutor<GoodsSpecificationEntity> {

}