package com.mouse.core.wx.param;

import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ; lidongdong
 * @Description 三方回调参数
 * @Date 2019-12-17
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.ALWAYS)
public class OrderToNotifyParam implements Serializable {
    private static final long serialVersionUID = 4620714734174867323L;
    /**
     * 订单id
     */
    String orderId;
    /**
     * 平台用户id
     */
    String userId;
    /**
     * 是否支付成功
     */
    Boolean notifyResult;
    /**
     * 3方支付 用户唯一标识
     */
    String userIdentify;
    /**
     * 3方支付返回支付金额
     */
    BigDecimal amount;
    /**
     * 3方支付回调订单号
     */
    String transactionId;

    String sign;
}
