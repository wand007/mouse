package com.mouse.api.service;

import com.mouse.dao.entity.order.OrderGoodsEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-22
 */
public interface OrderGoodsService {
    Optional<List<OrderGoodsEntity>> findByOrderId(String orderId);

    void add(OrderGoodsEntity orderGoodsEntity);
}
