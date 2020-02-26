package com.mouse.api.service.impl;

import com.mouse.api.commons.req.SaveOrderReq;
import com.mouse.api.service.OrderService;
import com.mouse.core.utils.SnowflakeIdWorker;
import com.mouse.dao.entity.order.OrderEntity;
import com.mouse.dao.repository.order.OrderGoodsRepository;
import com.mouse.dao.repository.order.OrderRepository;
import com.mouse.dao.repository.user.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
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
    SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderGoodsRepository orderGoodsRepository;
    @Autowired
    AddressRepository addressRepository;

    @Override
    public Optional<OrderEntity> findById(String id) {
        return orderRepository.findById(id);
    }

    @Override
    public Optional<List<OrderEntity>> findByUserId(String userId) {
        return orderRepository.findByUserIdAndDeleted(userId, false);
    }

    @Override
    public Page<OrderEntity> findByUserIdPage(String userId, List<Short> orderStatus, Integer pageNum, Integer pageSize) {
        Page<OrderEntity> page = orderRepository.findAll((Specification<OrderEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));
            if (userId != null) {
                expressions.add(criteriaBuilder.equal(root.<Integer>get("userId"), userId));
            }
            //订单状态
            if (!CollectionUtils.isEmpty(orderStatus)) {
                //订单状态
                CriteriaBuilder.In<Short> in = criteriaBuilder.in(root.<Short>get("orderStatus"));
                for (Short categoryId : orderStatus) {
                    in.value(categoryId);
                }
                expressions.add(criteriaBuilder.and(in));
            }
            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        return page;
    }

    @Override
    public Integer updateStatusAndEndTime(String id, Short orderStatus, LocalDateTime endTime) {
        return orderRepository.updateStatusAndEndTime(id, orderStatus, endTime);
    }

    @Override
    public Integer updateStatusAndConfirmTime(String id, Short orderStatus, LocalDateTime confirmTime) {
        return orderRepository.updateStatusAndConfirmTime(id, orderStatus, confirmTime);
    }

    @Override
    public Integer updateDeleteByOrderId(String orderId, Boolean deleted) {
        return orderRepository.updateDeleteByOrderId(orderId, deleted);
    }

    @Override
    public void add(OrderEntity order) {
        orderRepository.save(order);
    }


    @Override
    public Integer save(SaveOrderReq param) {

        return null;
    }
}
