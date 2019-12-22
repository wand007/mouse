package com.mouse.api.service.impl;

import com.mouse.api.commons.enums.PositionEnum;
import com.mouse.api.service.AdService;
import com.mouse.api.service.OrderService;
import com.mouse.dao.entity.operate.AdEntity;
import com.mouse.dao.entity.order.OrderEntity;
import com.mouse.dao.repository.operate.AdRepository;
import com.mouse.dao.repository.order.OrderGoodsRepository;
import com.mouse.dao.repository.order.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description 广告表
 * @Date 2019-12-15
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderGoodsRepository orderGoodsRepository;

    @Override
    public Optional<OrderEntity> findById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public Optional<List<OrderEntity>> findByUserId(Integer userId) {
        return orderRepository.findByUserIdAndDeleted(userId,false);
    }
}
