package com.mouse.api.commons.req;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ; lidongdong
 * @Description 订单表
 * @Date 2019-11-26
 */
@Data
public class SaveOrderReq implements Serializable {

    private static final long serialVersionUID = -5107368886185505088L;
    /**
     * 用户表的用户ID
     */
    private Integer userId;
    /**
     * 购物车记录ID
     */
    private Integer cartId;
    /**
     * 收货地址记录ID
     */
    private Integer addressId;
    /**
     * 优惠券ID
     */
    private Integer couponId;
    /**
     * 优惠券使用记录ID
     */
    private Integer userCouponId;
    /**
     * 拼团规则ID
     */
    private Integer grouponRulesId;
    /**
     * 发起团购的团购ID
     */
    private Integer grouponLinkId;
    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 用户订单留言
     */
    private String message;

    /**
     * 商品总费用
     */
    private BigDecimal goodsPrice;

    /**
     * 配送费用
     */
    private BigDecimal freightPrice;

    /**
     * 优惠券减免
     */
    private BigDecimal couponPrice;

    /**
     * 用户积分减免
     */
    private BigDecimal integralPrice;

    /**
     * 团购优惠价减免
     */
    private BigDecimal grouponPrice;

    /**
     * 订单费用， = goods_price + freight_price - coupon_price
     */
    private BigDecimal orderPrice;

    /**
     * 实付费用， = order_price - integral_price
     */
    private BigDecimal actualPrice;
}