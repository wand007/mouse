package com.mouse.api.service;

import com.mouse.dao.entity.order.CartEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description 用户购物车服务
 * @Date 2019-12-15
 */
public interface CartService {
    /**
     * 根据用户ID查询用户购物车记录
     *
     * @param userId
     * @return
     */
    Optional<List<CartEntity>> findByUserId(Integer userId);

    /**
     * 根据ID删除用户购物车记录
     *
     * @param id 用户购物车记录ID
     */
    void deleteById(Integer id);

    /**
     * 统计用户购物车商品数量
     *
     * @param userId 用户ID
     * @return
     */
    Long count(Integer userId);

    /**
     * 根据用户ID和商品ID和产品ID查询用户购物车记录
     *
     * @param productId 产品ID
     * @param userId    用户ID
     * @return
     */
    Optional<CartEntity> findByUserIdAndProductId(Integer userId, Integer productId);

    /**
     * 保存购物车记录
     *
     * @param userId    用户ID
     * @param productId 产品ID
     * @return
     */
    CartEntity save(Integer userId, Integer productId);

    /**
     * 修改购物车商品数量
     *
     * @param id     用户购物车记录ID
     * @param Number 商品数量
     */
    void updateNumberById(Integer id, Integer Number);

    /**
     * 修改商品选中状态
     *
     * @param userId     用户ID
     * @param productIds 产品ID集合
     * @param isChecked  选中状态
     */
    void updateChecked(Integer userId, List<Integer> productIds, Boolean isChecked);

    /**
     * 根据用户ID和产品ID集合删除购物车记录
     *
     * @param userId     用户ID
     * @param productIds 产品ID集合
     */
    void deleteByUserIdAndProductIdIn(Integer userId, List<String> productIds);

    Optional<List<CartEntity>> findByUserIdAndIsChecked(Integer userId, boolean checked);

    /**
     * 根据购物车记录ID购物车记录
     *
     * @param cartId 购物车记录ID
     * @return
     */
    Optional<CartEntity> findById(Integer cartId);

    void clearGoods(Integer userId, List<Integer> cartIds);
}
