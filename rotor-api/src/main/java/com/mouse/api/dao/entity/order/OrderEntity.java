package com.mouse.api.dao.entity.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author ; lidongdong
 * @Description 热闹关键词表实体
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_order")
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 5141824886455079945L;
    @Id
    @Column(name = "id", columnDefinition = "varchar(32) COMMENT '订单ID'", nullable = false)
    private String id;
    @Column(name = "order_sn", columnDefinition = "varchar(32) COMMENT '订单序列号'", nullable = false)
    private String orderSn;
    @Column(name = "user_id", columnDefinition = "varchar(32) COMMENT '会员Id'", nullable = false)
    private String userId;
    //订单状态
    //订单相关状态字段设计，采用单个字段表示全部的订单状态
    //1xx 表示订单取消和删除等状态 0订单创建成功等待付款，　101订单已取消，　102订单已删除
    //2xx 表示订单支付状态　201订单已付款，等待发货
    //3xx 表示订单物流相关状态　300订单已发货， 301用户确认收货
    //4xx 表示订单退换货相关的状态　401 没有发货，退款　402 已收货，退款退货
    @Column(name = "order_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '订单状态'", nullable = false)
    private Integer orderStatus;
    @Column(name = "shipping_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '发货状态 商品配送情况;0未发货,1已发货,2已收货,4退货'", nullable = false)
    private Integer shippingStatus;
    @Column(name = "pay_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '付款状态 支付状态;0未付款;1付款中;2已付款'", nullable = false)
    private Integer payStatus;
    @Column(name = "consignee", columnDefinition = "varchar(32) COMMENT '收货人'", nullable = false)
    private String consignee;
    @Column(name = "country", columnDefinition = "varchar(32) COMMENT '国家'", nullable = false)
    private String country;
    @Column(name = "province", columnDefinition = "varchar(32) COMMENT '省'", nullable = false)
    private String province;
    @Column(name = "city", columnDefinition = "varchar(32) COMMENT '地市'", nullable = false)
    private String city;
    @Column(name = "district", columnDefinition = "varchar(32) COMMENT '区县'", nullable = false)
    private String district;
    @Column(name = "address", columnDefinition = "varchar(32) COMMENT '收货地址'", nullable = false)
    private String address;
    @Column(name = "mobile", columnDefinition = "char(15) COMMENT '联系电话'", nullable = false)
    private String mobile;
    @Column(name = "postscript", columnDefinition = "varchar(32) COMMENT '补充说明'", nullable = false)
    private String postscript;
    @Column(name = "shipping_id", columnDefinition = "int unsigned default '0' COMMENT '快递公司Id'", nullable = false)
    private Integer shippingId;
    @Column(name = "shipping_name", columnDefinition = "varchar(32) COMMENT '快递公司名称'", nullable = false)
    private String shippingName;
    @Column(name = "shipping_no", columnDefinition = "varchar(32) COMMENT '快递单号'", nullable = false)
    private String shippingNo;
    @Column(name = "shipping_fee", columnDefinition = "decimal(10,2) default '0' COMMENT '快递费用'", nullable = false)
    private BigDecimal shippingFee;
    @Column(name = "pay_id", columnDefinition = "varchar(32) COMMENT '付款'", nullable = false)
    private String payId;
    @Column(name = "pay_name", columnDefinition = "varchar(32) COMMENT '付款方式'", nullable = false)
    private String payName;

    @Column(name = "actual_price", columnDefinition = "decimal(10,2) default '0' COMMENT '实际需要支付的金额'", nullable = false)
    private BigDecimal actualPrice;
    @Column(name = "integral", columnDefinition = "int unsigned default '0' COMMENT '积分'", nullable = false)
    private Integer integral;
    @Column(name = "integral_money", columnDefinition = "decimal(10,2) default '0' COMMENT '积分抵扣金额'", nullable = false)
    private BigDecimal integralMoney;
    @Column(name = "order_price", columnDefinition = "decimal(10,2) default '0' COMMENT '订单总价'", nullable = false)
    private BigDecimal orderPrice;
    @Column(name = "goods_price", columnDefinition = "decimal(10,2) default '0' COMMENT '商品总价'", nullable = false)
    private BigDecimal goodsPrice;
    @Column(name = "settlement_total_fee", columnDefinition = "decimal(10,2) default '0' COMMENT '单位分 应结订单金额 扣除手续后 到账金额'", nullable = false)
    private BigDecimal settlementTotalFee;
    @Column(name = "full_cut_price", columnDefinition = "decimal(10,2) default '0' COMMENT '订单满减金额'", nullable = false)
    private BigDecimal fullCutPrice;
    @Column(name = "confirm_time", columnDefinition = "datetime COMMENT '确认时间'")
    private Date confirmTime;
    @Column(name = "pay_time", columnDefinition = "datetime COMMENT '付款时间'")
    private Date payTime;
    @Column(name = "freight_price", columnDefinition = "decimal(10,2) default '0' COMMENT '配送费用'", nullable = false)
    private BigDecimal freightPrice;
    @Column(name = "coupon_id", columnDefinition = "int unsigned default '0' COMMENT '使用的优惠券id'", nullable = false)
    private Integer couponId;
    @Column(name = "all_order_id", columnDefinition = "int unsigned default '0' COMMENT '总订单ID'", nullable = false)
    private Integer allOrderId;
    @Column(name = "parent_id", columnDefinition = "int unsigned default '0' COMMENT '商品Id'", nullable = false)
    private Integer parentId;
    @Column(name = "brand_id", columnDefinition = "int unsigned default '0' COMMENT '品牌Id'", nullable = false)
    private Integer brandId;
    @Column(name = "promoter_id", columnDefinition = "int unsigned default '0' COMMENT '推荐人id'", nullable = false)
    private Integer promoterId;
    @Column(name = "coupon_price", columnDefinition = "decimal(10,2) default '0' COMMENT '优惠价格'", nullable = false)
    private BigDecimal couponPrice;
    @Column(name = "brokerage", columnDefinition = "decimal(10,2) default '0' COMMENT '分佣金额'", nullable = false)
    private BigDecimal brokerage;
    @Column(name = "callback_status", columnDefinition = "tinyint unsigned default '0' COMMENT '支付回调状态'", nullable = false)
    private Integer callbackStatus;

    @Column(name = "order_type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '订单类型 1：普通订单 2：团购订单 3：砍价订单 4: 直接购买'", nullable = false)
    private Integer orderType;

    @Column(name = "group_buying_id", columnDefinition = "varchar(32) COMMENT '团购组ID'", nullable = false)
    private Integer groupBuyingId;
    @Column(name = "merchant_id", columnDefinition = "varchar(32) COMMENT '供应商ID'", nullable = false)
    private String merchantId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
