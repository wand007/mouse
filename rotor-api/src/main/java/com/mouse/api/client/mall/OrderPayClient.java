package com.mouse.api.client.mall;

import com.mouse.api.base.GlobalExceptionHandler;
import com.mouse.api.commons.FootprintComm;
import com.mouse.api.commons.GoodsComm;
import com.mouse.api.commons.TaskComm;
import com.mouse.api.feign.mall.OrderPayFeign;
import com.mouse.api.service.*;
import com.mouse.core.base.BusinessException;
import com.mouse.core.base.R;
import com.mouse.core.enums.ReceiptChannelEnum;
import com.mouse.core.express.ExpressService;
import com.mouse.core.utils.RedisLock;
import com.mouse.core.utils.SnowflakeIdWorker;
import com.mouse.core.wx.WxJsPayCommon;
import com.mouse.dao.entity.order.OrderEntity;
import com.mouse.dao.entity.user.UserEntity;
import com.mouse.dao.utils.OrderHandleOption;
import com.mouse.dao.utils.OrderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author ; lidongdong
 * @Description 订单支付服务 API
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("order")
public class OrderPayClient extends GlobalExceptionHandler implements OrderPayFeign {


    @Autowired
    RedisLock redisLock;
    @Autowired
    SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    GoodsComm goodsComm;
    @Autowired
    FootprintComm footprintComm;
    @Autowired
    TaskComm taskComm;
    @Autowired
    WxJsPayCommon wxJsPayCommon;

    @Autowired
    UserService userService;
    @Autowired
    CartService cartService;
    @Autowired
    GoodsAttributeService goodsAttributeService;
    @Autowired
    GoodsSpecificationService goodsSpecificationService;
    @Autowired
    SearchHistoryService searchHistoryService;
    @Autowired
    GoodsIssueService goodsIssueService;
    @Autowired
    CommentService commentService;
    @Autowired
    GrouponService grouponService;
    @Autowired
    CouponService couponService;
    @Autowired
    CouponUserService couponUserService;
    @Autowired
    GrouponRulesService grouponRulesService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderGoodsService orderGoodsService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    ProductService productService;
    @Autowired
    ExpressService expressService;
    @Autowired
    AddressService addressService;
    @Autowired
    CollectService collectService;


    /**
     * 付款订单的预支付会话标识
     *
     * @param userId  用户ID
     * @param orderId 订单信息
     * @return 支付订单ID
     */
    @Override
    public R prepay(@RequestParam("userId") String userId, @RequestParam("orderId") String orderId) {
        return R.success();
    }

    /**
     * 订单支付
     *
     * @param orderId        订单ID
     * @param userId         用户ID
     * @param receiptChannel 支付渠道类型 com.hvyogo.commons.h.eunms.ReceiptChannelEnum
     * @return
     */
    @Override
    public R payOrder(@RequestParam("orderId") String orderId,
                      @RequestParam("userId") String userId,
                      @RequestParam("receiptChannel") ReceiptChannelEnum receiptChannel) {

        OrderEntity orderEntity = orderService.findById(orderId).orElseThrow(() -> new BusinessException("订单记录不存在"));
        if (!orderEntity.getUserId().equals(userId)) {
            return R.error("不能支付ta人的订单");
        }

        // 检测是否能够取消
        OrderHandleOption handleOption = OrderUtil.build(orderEntity);
        if (!handleOption.isPay()) {
            return R.error("订单不能支付");
        }

        UserEntity userEntity = userService.findById(orderEntity.getUserId()).orElseThrow(() -> new BusinessException("用户记录不存在"));

        Map<String, String> unifiedOrderMap = null;
        String payOrderId = snowflakeIdWorker.nextId();
        try {
            // 生成三方支付信息
            unifiedOrderMap = wxJsPayCommon.initPayMap(payOrderId, orderEntity.getActualPrice(), userEntity.getWeixinOpenid(), "mall服务", orderId);
        } catch (Exception e) {
            log.error("生成第三方支付信息异常,id:" + orderEntity.getId(), e);
            return R.error("第三方支付信息异常");
        }


        return R.success(unifiedOrderMap);
    }


    /**
     * 订单申请退款
     *
     * @param userId  用户ID
     * @param orderId 订单信息，{ orderId：xxx }
     * @return 订单退款操作结果
     */
    @Override
    public R refund(@RequestParam("userId") String userId,
                    @RequestParam("orderId") String orderId) {
        OrderEntity orderEntity = orderService.findById(orderId).orElseThrow(() -> new BusinessException("订单记录不存在"));
        if (!orderEntity.getUserId().equals(userId)) {
            return R.error("不能支付ta人的订单");
        }
        OrderHandleOption handleOption = OrderUtil.build(orderEntity);
        if (!handleOption.isRefund()) {
            return R.error("订单不能取消");
        }
        // 设置订单申请退款状态
        orderEntity.setOrderStatus(OrderUtil.STATUS_REFUND);
        orderEntity.setEndTime(LocalDateTime.now());
        if (orderService.updateStatusAndEndTime(orderEntity.getId(), orderEntity.getOrderStatus(), orderEntity.getEndTime()) == 0) {
            return R.error("订单取消失败");
        }
        //TODO 发送邮件和短信通知，这里采用异步发送
        // 有用户申请退款，邮件通知运营人员
        return R.success();
    }
}
