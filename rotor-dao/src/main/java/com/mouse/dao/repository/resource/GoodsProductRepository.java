package com.mouse.dao.repository.resource;

import com.mouse.dao.entity.resource.GoodsProductEntity;
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
public interface GoodsProductRepository extends JpaRepository<GoodsProductEntity, Integer>, JpaSpecificationExecutor<GoodsProductEntity> {
    /**
     * 根据商品ID查询商品货品表
     *
     * @param goodsId 商品ID
     * @return
     */
    Optional<List<GoodsProductEntity>> findByGoodsIdAndDeleted(Integer goodsId, Boolean deleted);
}