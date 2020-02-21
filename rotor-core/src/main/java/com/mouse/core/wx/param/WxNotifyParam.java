package com.mouse.core.wx.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-31
 */
@Data
public class WxNotifyParam implements Serializable {

    private static final long serialVersionUID = 1827260312087073307L;
    /**
     * 订单还
     */
    private String out_trade_no;

    /**
     * openid
     */
    private String openid;

    /**
     * 总价
     */
    private String total_fee;

    /**
     * 微信流水号
     */
    private String transaction_id;

    /**
     * 支付完成时间
     */
    private String time_end;

    /**
     * 是否关注公众号
     */
    private String is_subscribe;

    /**
     * 是否支付成功
     */
    private Boolean payFlag;

    private String msg;
}
