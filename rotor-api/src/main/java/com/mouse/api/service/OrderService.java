package com.mouse.api.service;

import com.mouse.api.commons.req.SaveOrderReq;
import com.mouse.core.params.dto.OrderPriceDTO;
import com.mouse.dao.entity.order.OrderEntity;
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

    Optional<List<OrderEntity>> findByUserId(String userId);

    Page<OrderEntity> findByUserIdPage(String userId, List<Short> orderStatus, Integer pageNum, Integer pageSize);

    String save(OrderPriceDTO orderPriceDTO, SaveOrderReq param);

    void add(OrderEntity order);

    Integer updateStatusAndEndTime(String id, Short orderStatus, LocalDateTime endTime);

    Integer updateStatusAndConfirmTime(String id, Short orderStatus, LocalDateTime confirmTime);

    Integer updateDeleteByOrderId(String orderId, Boolean deleted);


}
