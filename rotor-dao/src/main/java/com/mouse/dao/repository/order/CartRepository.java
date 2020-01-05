package com.mouse.dao.repository.order;

import com.mouse.dao.entity.order.CartEntity;
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
public interface CartRepository extends JpaRepository<CartEntity, Integer>, JpaSpecificationExecutor<CartEntity> {


    /**
     * 根据用户ID查询用户购物车记录
     *
     * @param userId
     * @return
     */
    Optional<List<CartEntity>> findByUserId(Integer userId);

    /**
     * 根据用户ID和商品ID和产品ID查询用户购物车记录
     *
     * @param goodsId   商品ID
     * @param productId 产品ID
     * @param userId    用户ID
     * @return
     */
    Optional<CartEntity> findByUserIdAndGoodsIdAndProductId(Integer userId, Integer goodsId, Integer productId);

    /**
     * 修改购物车中商品数量
     *
     * @param id     购物车记录ID
     * @param number 商品数量
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class, timeout = 30, isolation = Isolation.READ_COMMITTED)
    @Query(value = "update CartEntity r set r.number = ?2, r.updateTime = now() where r.id = ?1")
    Integer updateNumberById(Integer id, Integer number);
}