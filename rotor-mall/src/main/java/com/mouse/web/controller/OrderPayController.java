package com.mouse.web.controller;

import com.mouse.api.feign.mall.OrderPayFeign;
import com.mouse.core.base.R;
import com.mouse.core.enums.ReceiptChannelEnum;
import com.mouse.core.params.RotorSessionUser;
import com.mouse.web.base.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author ; lidongdong
 * @Description 订单支付服务
 * @Date 2020-01-28
 */

@Slf4j
@Validated
@RestController
@RequestMapping("order")
public class OrderPayController extends GlobalExceptionHandler {
    @Autowired
    OrderPayFeign orderPayFeign;


    /**
     * 付款订单的预支付会话标识
     *
     * @param orderId 订单信息，{ orderId：xxx }
     * @return 支付订单ID
     */
    @PostMapping("prepay")
    public R prepay(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestParam("orderId") String orderId) {
        return orderPayFeign.prepay(sessionUser.getId(), orderId);
    }


    /**
     * 代开订单支付
     *
     * @param sessionUser
     * @param orderId     订单ID
     * @return
     */
    @PostMapping(value = "/payOrder")
    public R payOrder(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                      @RequestParam("orderId") String orderId) {
        return orderPayFeign.payOrder(orderId, sessionUser.getId(), ReceiptChannelEnum.WX_PAY_JS);
    }


    /**
     * 订单申请退款
     *
     * @param orderId 订单信息，{ orderId：xxx }
     * @return 订单退款操作结果
     */
    @PostMapping("refund")
    public R refund(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestParam("orderId") String orderId) {
        return orderPayFeign.refund(sessionUser.getId(), orderId);
    }

}
