package com.mouse.api.dao.entity.oms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ; lidongdong
 * @Description 订单表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "oms_order")
public class OmsOrderEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", columnDefinition = "bigint(20) unsigned COMMENT '会员ID'", nullable = false)
    private Long memberId;

    @Column(name = "coupon_id", columnDefinition = "bigint(20) unsigned COMMENT '优惠券ID'", nullable = false)
    private Long couponId;

    @Column(name = "order_sn", columnDefinition = "varchar(64) COMMENT '订单编号'", nullable = false)
    private String orderSn;

    @Column(name = "member_username", columnDefinition = "varchar(64) COMMENT '用户帐号'", nullable = false)
    private String memberUsername;

    @Column(name = "total_amount", columnDefinition = "decimal(10,2) default '0' COMMENT '订单总金额'", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "pay_amount", columnDefinition = "decimal(10,2) default '0' COMMENT '应付金额（实际支付金额）'", nullable = false)
    private BigDecimal payAmount;

    @Column(name = "freight_amount", columnDefinition = "decimal(10,2) default '0' COMMENT '运费金额'", nullable = false)
    private BigDecimal freightAmount;

    @Column(name = "promotion_amount", columnDefinition = "decimal(10,2) default '0' COMMENT '促销优化金额（促销价、满减、阶梯价）'", nullable = false)
    private BigDecimal promotionAmount;

    @Column(name = "integration_amount", columnDefinition = "decimal(10,2) default '0' COMMENT '积分抵扣金额'", nullable = false)
    private BigDecimal integrationAmount;

    @Column(name = "coupon_amount", columnDefinition = "decimal(10,2) default '0' COMMENT '优惠券抵扣金额'", nullable = false)
    private BigDecimal couponAmount;

    @Column(name = "discount_amount", columnDefinition = "decimal(10,2) default '0' COMMENT '管理员后台调整订单使用的折扣金额'", nullable = false)
    private BigDecimal discountAmount;

    @Column(name = "pay_type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '支付方式：0->未支付；1->支付宝；2->微信'", nullable = false)
    private Integer payType;

    @Column(name = "source_type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '订单来源：0->PC订单；1->app订单'", nullable = false)
    private Integer sourceType;

    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单'", nullable = false)
    private Integer status;

    @Column(name = "order_type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '订单类型：0->正常订单；1->秒杀订单'", nullable = false)
    private Integer orderType;

    @Column(name = "delivery_company", columnDefinition = "varchar(64) COMMENT '物流公司(配送方式)'", nullable = false)
    private String deliveryCompany;

    @Column(name = "delivery_sn", columnDefinition = "varchar(64) COMMENT '物流单号'", nullable = false)
    private String deliverySn;

    @Column(name = "auto_confirm_day", columnDefinition = "int unsigned COMMENT '自动确认时间（天）'", nullable = false)
    private Integer autoConfirmDay;

    @Column(name = "integration", columnDefinition = "int unsigned COMMENT '可以获得的积分'", nullable = false)
    private Integer integration;

    @Column(name = "growth", columnDefinition = "int unsigned COMMENT '可以活动的成长值'", nullable = false)
    private Integer growth;

    @Column(name = "promotion_info", columnDefinition = "varchar(64) COMMENT '活动信息'", nullable = false)
    private String promotionInfo;

    @Column(name = "bill_type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '发票类型：0->不开发票；1->电子发票；2->纸质发票'", nullable = false)
    private Integer billType;

    @Column(name = "bill_header", columnDefinition = "varchar(200) COMMENT '发票抬头'", nullable = false)
    private String billHeader;

    @Column(name = "bill_content", columnDefinition = "varchar(200) COMMENT '发票内容'", nullable = false)
    private String billContent;

    @Column(name = "bill_receiver_phone", columnDefinition = "varchar(64) COMMENT '收票人电话'", nullable = false)
    private String billReceiverPhone;

    @Column(name = "bill_receiver_email", columnDefinition = "varchar(64) COMMENT '收票人邮箱'", nullable = false)
    private String billReceiverEmail;

    @Column(name = "receiver_name", columnDefinition = "varchar(64) COMMENT '收货人姓名'", nullable = false)
    private String receiverName;

    @Column(name = "receiver_phone", columnDefinition = "varchar(64) COMMENT '收货人电话'", nullable = false)
    private String receiverPhone;

    @Column(name = "receiver_post_code", columnDefinition = "varchar(64) COMMENT '收货人邮编'", nullable = false)
    private String receiverPostCode;

    @Column(name = "receiver_province", columnDefinition = "varchar(64) COMMENT '省份/直辖市'", nullable = false)
    private String receiverProvince;

    @Column(name = "receiver_city", columnDefinition = "varchar(64) COMMENT '城市'", nullable = false)
    private String receiverCity;

    @Column(name = "receiver_region", columnDefinition = "varchar(64) COMMENT '区'", nullable = false)
    private String receiverRegion;

    @Column(name = "receiver_detail_address", columnDefinition = "varchar(64) COMMENT '详细地址'", nullable = false)
    private String receiverDetailAddress;

    @Column(name = "note", columnDefinition = "varchar(64) COMMENT '订单备注'", nullable = false)
    private String note;

    @Column(name = "confirm_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '确认收货状态：0->未确认；1->已确认'", nullable = false)
    private Integer confirmStatus;

    @Column(name = "delete_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '删除状态：0->未删除；1->已删除'", nullable = false)
    private Integer deleteStatus;

    @Column(name = "use_integration", columnDefinition = "int unsigned COMMENT '下单时使用的积分'", nullable = false)
    private Integer useIntegration;

    @Column(name = "payment_time", columnDefinition = "datetime COMMENT '支付时间'")
    private Date paymentTime;

    @Column(name = "delivery_time", columnDefinition = "datetime COMMENT '发货时间'")
    private Date deliveryTime;

    @Column(name = "receive_time", columnDefinition = "datetime COMMENT '确认收货时间'")
    private Date receiveTime;

    @Column(name = "comment_time", columnDefinition = "datetime COMMENT '评价时间'")
    private Date commentTime;

}