package com.mouse.api.feign.mall;

import com.mouse.api.hystrix.mall.HystrixOrderPayFeign;
import com.mouse.core.base.R;
import com.mouse.core.enums.ReceiptChannelEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ; lidongdong
 * @Description 订单支付服务 feign
 * @Date 2020-01-28
 */
@FeignClient(name = "http://ROTOR-API",
        path = "rotor-api/order/pay",
        fallbackFactory = HystrixOrderPayFeign.class)
public interface OrderPayFeign {


    /**
     * 付款订单的预支付会话标识
     *
     * @param userId  用户ID
     * @param orderId 订单信息，{ orderId：xxx }
     * @return 支付订单ID
     */
    @PostMapping("prepay")
    R prepay(@RequestParam(name = "userId") String userId,
             @RequestParam(name = "orderId") String orderId);

    /**
     * 订单支付
     *
     * @param orderId        订单ID
     * @param userId         用户ID
     * @param receiptChannel 支付渠道类型 com.hvyogo.commons.h.eunms.ReceiptChannelEnum
     * @return
     */
    @PostMapping(value = "/payOrder", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    R payOrder(@RequestParam("orderId") String orderId,
               @RequestParam("userId") String userId,
               @RequestParam("receiptChannel") ReceiptChannelEnum receiptChannel);

    /**
     * 订单申请退款
     *
     * @param userId  用户ID
     * @param orderId 订单信息，{ orderId：xxx }
     * @return 订单退款操作结果
     */
    @PostMapping("refund")
    R refund(@RequestParam(name = "userId") String userId,
             @RequestParam(name = "orderId") String orderId);
}
