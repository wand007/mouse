package com.mouse.dao.repository.resource;

import com.mouse.dao.entity.resource.GoodsSpecificationEntity;
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
public interface GoodsSpecificationRepository extends JpaRepository<GoodsSpecificationEntity, Integer>, JpaSpecificationExecutor<GoodsSpecificationEntity> {
    /**
     * 根据商品ID查询商品规格
     *
     * @param goodsId 商品ID
     * @return
     */
    Optional<List<GoodsSpecificationEntity>> findByGoodsIdAndDeleted(Integer goodsId, Boolean deleted);
}