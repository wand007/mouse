package com.mouse.api.service;

import com.mouse.api.commons.req.SaveOrderReq;
import com.mouse.dao.entity.order.OrderEntity;
import com.mouse.dao.entity.user.UserEntity;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
public interface OrderService {
    Optional<OrderEntity> findById(String id);

    Optional<List<OrderEntity>> findByUserId(Integer userId);

    Page<OrderEntity> findByUserIdPage(Integer userId, List<Short> orderStatus, Integer pageNum, Integer pageSize);

    Integer save(SaveOrderReq param);

    void add(OrderEntity order);

    Integer updateStatusAndEndTime(String id, Short orderStatus, LocalDateTime endTime);
}
