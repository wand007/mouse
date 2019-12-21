package com.mouse.dao.repository.resource;

import com.mouse.dao.entity.resource.GoodsAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface GoodsAttributeRepository extends JpaRepository<GoodsAttributeEntity, Integer>, JpaSpecificationExecutor<GoodsAttributeEntity> {
    /**
     * 根据商品ID获取商品属性
     *
     * @param goodsId 商品ID
     * @return
     */
    Optional<List<GoodsAttributeEntity>> findByGoodsIdAndDeleted(Integer goodsId, Boolean deleted);
}