package com.mouse.dao.entity.order;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 订单表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_order")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 8354296396321446889L;
    @Id
    @Column(name = "id", columnDefinition = "varchar(32) COMMENT '订单ID'", nullable = false)
    private String id;

    @Column(name = "user_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '用户表的用户ID'", nullable = false)
    private Integer userId;

    @Column(name = "order_sn", columnDefinition = "char(15) COMMENT '订单编号'", nullable = false)
    private String orderSn;

    @Column(name = "order_status", columnDefinition = "smallint unsigned DEFAULT '0' COMMENT '订单状态'", nullable = false)
    private Short orderStatus;

    @Column(name = "consignee", columnDefinition = "char(15) COMMENT '收货人名称'", nullable = false)
    private String consignee;

    @Column(name = "mobile", columnDefinition = "char(15) COMMENT '收货人手机号'", nullable = false)
    private String mobile;

    @Column(name = "address", columnDefinition = "char(15) COMMENT '收货具体地址'", nullable = false)
    private String address;

    @Column(name = "message", columnDefinition = "char(15) COMMENT '用户订单留言'", nullable = false)
    private String message;

    @Column(name = "goods_price", columnDefinition = "decimal(10,2) default '0' COMMENT '商品总费用'", nullable = false)
    private BigDecimal goodsPrice;

    @Column(name = "freight_price", columnDefinition = "decimal(10,2) default '0' COMMENT '配送费用'", nullable = false)
    private BigDecimal freightPrice;

    @Column(name = "coupon_price", columnDefinition = "decimal(10,2) default '0' COMMENT '优惠券减免'", nullable = false)
    private BigDecimal couponPrice;

    @Column(name = "integral_price", columnDefinition = "decimal(10,2) default '0' COMMENT '用户积分减免'", nullable = false)
    private BigDecimal integralPrice;

    @Column(name = "groupon_price", columnDefinition = "decimal(10,2) default '0' COMMENT '团购优惠价减免'", nullable = false)
    private BigDecimal grouponPrice;

    @Column(name = "order_price", columnDefinition = "decimal(10,2) default '0' COMMENT '订单费用， = goods_price + freight_price - coupon_price'", nullable = false)
    private BigDecimal orderPrice;

    @Column(name = "actual_price", columnDefinition = "decimal(10,2) default '0' COMMENT '实付费用， = order_price - integral_price'", nullable = false)
    private BigDecimal actualPrice;

    @Column(name = "pay_id", columnDefinition = "char(15) COMMENT '微信付款编号'", nullable = false)
    private String payId;

    @Column(name = "pay_time", columnDefinition = "datetime COMMENT '微信付款时间'", nullable = false)
    private LocalDateTime payTime;

    @Column(name = "ship_sn", columnDefinition = "char(15) COMMENT '发货编号'", nullable = false)
    private String shipSn;

    @Column(name = "ship_channel", columnDefinition = "char(15) COMMENT '发货快递公司'", nullable = false)
    private String shipChannel;

    @Column(name = "ship_time", columnDefinition = "datetime COMMENT '发货开始时间'", nullable = false)
    private LocalDateTime shipTime;

    @Column(name = "confirm_time", columnDefinition = "datetime COMMENT '用户确认收货时间'", nullable = false)
    private LocalDateTime confirmTime;

    @Column(name = "comments", columnDefinition = "smallint unsigned DEFAULT '0' COMMENT '待评价订单商品数量'", nullable = false)
    private Short comments;

    @Column(name = "end_time", columnDefinition = "datetime COMMENT '订单关闭时间'", nullable = false)
    private LocalDateTime endTime;


    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

    @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除 0 未删除，1 删除'", nullable = false)
    private Boolean deleted;
}