package com.mouse.dao.repository.resource;

import com.mouse.dao.entity.resource.GoodsEntity;
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
public interface GoodsRepository extends JpaRepository<GoodsEntity, Integer>, JpaSpecificationExecutor<GoodsEntity> {
    /**
     * 统计再售商品数量
     *
     * @param isOnSale 再售状态
     * @param deleted
     * @return
     */
    Integer countByIsOnSaleAndDeleted(Boolean isOnSale, Boolean deleted);

    /**
     * 统计再售商品详情
     *
     * @param goodsId  商品ID
     * @param isOnSale 再售状态
     * @param deleted
     * @return
     */
    Optional<GoodsEntity> countByIdAndIsOnSaleAndDeleted(String goodsId, Boolean isOnSale, Boolean deleted);

    /**
     * 统计再售商品详情
     *
     * @param isOnSale 再售状态
     * @param deleted
     * @param goodsIds 商品ID集合
     * @return
     */
    Optional<List<GoodsEntity>> findByIsOnSaleAndDeletedAndIdIn(boolean isOnSale, boolean deleted, List<String> goodsIds);

    /**
     * 根据商品ID集合查询商品信息
     *
     * @param goodsIds 商品ID集合
     * @return
     */
    Optional<List<GoodsEntity>> findByIdIn(List<Integer> goodsIds);


}