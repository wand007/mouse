package com.mouse.api.task;

import com.mouse.api.service.OrderGoodsService;
import com.mouse.api.service.OrderService;
import com.mouse.api.service.ProductService;
import com.mouse.api.system.SystemConfig;
import com.mouse.core.base.BusinessException;
import com.mouse.core.task.Task;
import com.mouse.dao.entity.order.OrderEntity;
import com.mouse.dao.entity.order.OrderGoodsEntity;
import com.mouse.dao.utils.OrderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2020-02-14
 */
@Slf4j
@Component
public class OrderUnpaidTask extends Task {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderGoodsService orderGoodsService;
    @Autowired
    ProductService productService;

    private String orderId;

    public OrderUnpaidTask(String orderId, long delayInMilliseconds) {
        super("OrderUnpaidTask-" + orderId, delayInMilliseconds);
        this.orderId = orderId;
    }

    public OrderUnpaidTask(String orderId) {
        super("OrderUnpaidTask-" + orderId, SystemConfig.getOrderUnpaid() * 60 * 1000);
        this.orderId = orderId;
    }

    @Override
    public void run() {
        log.info("系统开始处理延时任务---订单超时未付款---" + this.orderId);


        OrderEntity orderEntity = orderService.findById(this.orderId).orElseThrow(() -> {
            log.info("orderId:{}", orderId);
            return new BusinessException("订单记录不存在");
        });
        if (!OrderUtil.isCreateStatus(orderEntity)) {
            return;
        }

        // 设置订单已取消状态
        orderEntity.setOrderStatus(OrderUtil.STATUS_AUTO_CANCEL);
        orderEntity.setEndTime(LocalDateTime.now());
        if (orderService.updateStatusAndEndTime(orderEntity.getId(), orderEntity.getOrderStatus(), orderEntity.getEndTime()) == 0) {
            throw new BusinessException("更新数据已失效");
        }

        // 商品货品数量增加
        List<OrderGoodsEntity> orderGoodsEntities = orderGoodsService.findByOrderId(orderId).orElseGet(() -> new ArrayList<>());
        for (OrderGoodsEntity orderGoodsEntity : orderGoodsEntities) {
            Integer productId = orderGoodsEntity.getProductId();
            Short number = orderGoodsEntity.getNumber();
            if (productService.reduceStock(productId, number) == 0) {
                throw new BusinessException("商品货品库存增加失败");
            }
        }
        log.info("系统结束处理延时任务---订单超时未付款---" + this.orderId);
    }
}
