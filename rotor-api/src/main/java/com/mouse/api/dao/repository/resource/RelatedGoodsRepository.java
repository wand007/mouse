package com.mouse.api.dao.repository.resource;

import com.mouse.api.dao.entity.resource.RelatedGoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface RelatedGoodsRepository extends JpaRepository<RelatedGoodsEntity, Integer>, JpaSpecificationExecutor<RelatedGoodsEntity> {
}
