package com.mouse.api.service;

import com.mouse.dao.entity.order.OrderEntity;
import com.mouse.dao.entity.user.UserEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
public interface OrderService {
    Optional<OrderEntity> findById(Integer id);

    Optional<List<OrderEntity>> findByUserId(Integer userId);
}
