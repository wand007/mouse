package com.mouse.dao.repository.resource;

import com.mouse.dao.entity.resource.GoodsProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 修改产品库存
     *
     * @param productId 产品ID
     * @param number    库存修改数量
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class, timeout = 30, isolation = Isolation.READ_COMMITTED)
    @Query(nativeQuery = true, value = "update tbl_goods_product  set number = number + ?2, update_time = now()  where id = ?1 and number >= ?2 ")
    Integer updateStock(Integer productId, Integer number);


}