package com.mouse.dao.repository.resource;

import com.mouse.dao.entity.resource.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface GoodsRepository extends JpaRepository<GoodsEntity, Integer>, JpaSpecificationExecutor<GoodsEntity> {
    /**
     * 统计再售商品数量
     *
     * @param isOnSale
     * @param deleted
     * @return
     */
    Integer countByIsOnSaleAndDeleted(Boolean isOnSale, Boolean deleted);

    Optional<GoodsEntity> countByIdAndIsOnSaleAndDeleted(String goodsId, Boolean isOnSale, Boolean deleted);
}