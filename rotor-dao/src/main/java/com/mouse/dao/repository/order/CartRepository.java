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
     * 根据用户ID和产品ID查询用户购物车记录
     *
     * @param productId 产品ID
     * @param userId    用户ID
     * @return
     */
    Optional<CartEntity> findByUserIdAndProductId(Integer userId, Integer productId);

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

    /**
     * 修改商品选中状态
     *
     * @param userId     用户ID
     * @param productIds 产品ID集合
     * @param isChecked  选中状态
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class, timeout = 30, isolation = Isolation.READ_COMMITTED)
    @Query(value = "update CartEntity r set r.isChecked = ?3, r.updateTime = now() where r.userId = ?1 and r.productId in (?2)")
    Integer updateChecked(Integer userId, List<Integer> productIds, Boolean isChecked);

    /**
     * 删除购物车记录
     *
     * @param userId     用户ID
     * @param productIds 产品ID集合
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class, timeout = 30, isolation = Isolation.READ_COMMITTED)
    @Query(value = "delete from CartEntity r  where r.userId = ?1 and r.productId in (?2)")
    Integer deleteByUserIdAndProductIdIn(Integer userId, List<String> productIds);

    /**
     * 查询用户选中的购物车记录
     *
     * @param userId  用户ID
     * @param checked 选中状态
     * @return
     */
    Optional<List<CartEntity>> findByUserIdAndIsChecked(Integer userId, boolean checked);
}