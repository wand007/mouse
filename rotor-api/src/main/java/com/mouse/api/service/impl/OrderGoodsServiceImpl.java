package com.mouse.api.service.impl;

import com.mouse.api.service.OrderGoodsService;
import com.mouse.dao.entity.order.OrderGoodsEntity;
import com.mouse.dao.repository.order.OrderGoodsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-22
 */
@Slf4j
@Service
public class OrderGoodsServiceImpl implements OrderGoodsService {
    @Autowired
    OrderGoodsRepository orderGoodsRepository;


    @Override
    public Optional<List<OrderGoodsEntity>> findByOrderId(Integer orderId) {
        return orderGoodsRepository.findByOrderIdAndDeleted(orderId,false);
    }
}
