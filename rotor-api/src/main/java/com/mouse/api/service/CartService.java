package com.mouse.api.service;

import com.mouse.api.commons.req.SaveCartReq;
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
     * @param goodsId   商品ID
     * @param productId 产品ID
     * @param userId    用户ID
     * @return
     */
    Optional<CartEntity> findByUserIdAndGoodsIdAndProductId(Integer goodsId, Integer productId, Integer userId);

    void save(Integer userId,Integer productId);

    void updateNumberById(Integer id, Integer Number);
}
