package com.mouse.api.service.impl;

import com.mouse.api.commons.req.SaveOrderReq;
import com.mouse.api.service.OrderService;
import com.mouse.core.enums.CouponUserEnum;
import com.mouse.core.enums.GrouponStatusEnum;
import com.mouse.core.params.dto.OrderPriceDTO;
import com.mouse.core.utils.GeneratID;
import com.mouse.core.utils.SnowflakeIdWorker;
import com.mouse.dao.entity.operate.CouponUserEntity;
import com.mouse.dao.entity.operate.GrouponEntity;
import com.mouse.dao.entity.order.CartEntity;
import com.mouse.dao.entity.order.OrderEntity;
import com.mouse.dao.entity.order.OrderGoodsEntity;
import com.mouse.dao.entity.user.AddressEntity;
import com.mouse.dao.repository.operate.CouponUserRepository;
import com.mouse.dao.repository.operate.GrouponRepository;
import com.mouse.dao.repository.order.CartRepository;
import com.mouse.dao.repository.order.OrderGoodsRepository;
import com.mouse.dao.repository.order.OrderRepository;
import com.mouse.dao.repository.resource.GoodsProductRepository;
import com.mouse.dao.repository.user.AddressRepository;
import com.mouse.dao.utils.OrderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
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
    CartRepository cartRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderGoodsRepository orderGoodsRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CouponUserRepository couponUserRepository;
    @Autowired
    GrouponRepository grouponRepository;
    @Autowired
    GoodsProductRepository goodsProductRepository;

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
    @Transactional(rollbackFor = Exception.class, timeout = 30, isolation = Isolation.REPEATABLE_READ)
    public String save(OrderPriceDTO orderPriceDTO, SaveOrderReq param) {
        String orderId = snowflakeIdWorker.nextId();
        String userId = param.getUserId();
        AddressEntity addressEntity = addressRepository.findByIdAndUserId(param.getAddressId(), userId).get();
        Integer couponId = param.getCouponId();
        Integer grouponRulesId = param.getGrouponRulesId();
        Integer grouponLinkId = param.getGrouponLinkId();
        // 如果使用了优惠券，设置优惠券使用状态
        if (couponId != 0 && couponId != -1) {
            CouponUserEntity couponUser = couponUserRepository.findById(param.getUserCouponId())
                    .orElseGet(() -> new CouponUserEntity());
            couponUser.setStatus(CouponUserEnum.STATUS_USED.getCode());
            couponUser.setUsedTime(LocalDateTime.now());
            couponUser.setOrderId(orderId);
            couponUserRepository.save(couponUser);
        }

        //如果是团购项目，添加团购信息
        if (grouponRulesId != null && grouponRulesId > 0) {
            GrouponEntity groupon = new GrouponEntity();
            groupon.setOrderId(orderId);
            groupon.setStatus(GrouponStatusEnum.NONE.getCode());
            groupon.setUserId(userId);
            groupon.setRulesId(grouponRulesId);
            if (grouponLinkId != null && grouponLinkId > 0) {
                //参与的团购记录
                GrouponEntity baseGroupon = grouponRepository.findById(grouponLinkId).orElseGet(() -> new GrouponEntity());
                groupon.setCreatorUserId(baseGroupon.getCreatorUserId());
                groupon.setGrouponId(grouponLinkId);
                groupon.setShareUrl(baseGroupon.getShareUrl());
            } else {
                groupon.setCreatorUserId(userId);
                groupon.setCreatorUserTime(LocalDateTime.now());
                groupon.setGrouponId(0);
            }
            grouponRepository.save(groupon);
        }

        List<CartEntity> cartEntities = cartRepository.findByIdIn(param.getCartIds()).get();
        // 订单
        OrderEntity order = new OrderEntity();
        order.setId(orderId);
        order.setUserId(userId);
        order.setOrderSn(GeneratID.getGeneratID());
        order.setOrderStatus(OrderUtil.STATUS_CREATE);
        order.setConsignee(addressEntity.getName());
        order.setMobile(addressEntity.getTel());
        order.setMessage(param.getMessage());
        order.setAddress(addressEntity.onAddressDetail());
        order.setGoodsPrice(orderPriceDTO.getCheckedGoodsPrice());
        order.setFreightPrice(orderPriceDTO.getFreightPrice());
        order.setCouponPrice(orderPriceDTO.getCouponPrice());
        order.setIntegralPrice(orderPriceDTO.getIntegralPrice());
        order.setOrderPrice(orderPriceDTO.getOrderTotalPrice());
        order.setActualPrice(orderPriceDTO.getActualPrice());
        order.setComments(cartEntities.size());
        order.setDeleted(false);
        order.setGrouponPrice(orderPriceDTO.getGrouponPrice());
        // 添加订单表项
        orderRepository.save(order);

        for (CartEntity cartEntity : cartEntities) {
            // 订单商品
            OrderGoodsEntity orderGoods = new OrderGoodsEntity();
            orderGoods.setOrderId(order.getId());
            orderGoods.setGoodsId(cartEntity.getGoodsId());
            orderGoods.setGoodsSn(cartEntity.getGoodsSn());
            orderGoods.setProductId(cartEntity.getProductId());
            orderGoods.setGoodsName(cartEntity.getGoodsName());
            orderGoods.setPicUrl(cartEntity.getPicUrl());
            orderGoods.setPrice(cartEntity.getPrice());
            orderGoods.setNumber(cartEntity.getNumber());
            orderGoods.setSpecifications(cartEntity.getSpecifications());
            orderGoods.setAddTime(LocalDateTime.now());
            orderGoods.setChecked(cartEntity.getChecked());
            orderGoods.setComment(cartEntity.getNumber());
            orderGoods.setDeleted(false);
            orderGoodsRepository.save(orderGoods);
            //减库存
            goodsProductRepository.updateStock(cartEntity.getProductId(), -cartEntity.getNumber());
        }
        // 删除购物车里面的商品信息
        cartRepository.deleteByUserIdAndIdIn(userId, param.getCartIds());
        return orderId;
    }
}
