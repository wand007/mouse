package com.mouse.api.dao.repository.resource;

import com.mouse.api.dao.entity.resource.GoodsSpecificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 商品对应规格表值表实体
 * @Date 2019-11-26
 */
@Repository
public interface GoodsSpecificationRepository extends JpaRepository<GoodsSpecificationEntity, Integer>, JpaSpecificationExecutor<GoodsSpecificationEntity> {


}
