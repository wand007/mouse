package com.mouse.api.dao.entity.sms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ; lidongdong
 * @Description 优惠卷表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "sms_coupon")
public class SmsCouponEntity implements Serializable {
    private static final long serialVersionUID = 8559093413333818452L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '优惠卷类型；0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券'", nullable = false)
    private Integer type;

    @Column(name = "code", columnDefinition = "varchar(64) COMMENT '优惠码'", nullable = false)
    private String code;

    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '优惠券名称'", nullable = false)
    private String name;

    @Column(name = "platform", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '使用平台：0->全部；1->移动；2->PC'", nullable = false)
    private Integer platform;

    @Column(name = "count", columnDefinition = "int unsigned COMMENT '数量'", nullable = false)
    private Integer count;

    @Column(name = "amount", columnDefinition = "decimal(10,2) default '0' COMMENT '金额'", nullable = false)
    private BigDecimal amount;

    @Column(name = "per_limit", columnDefinition = "int unsigned COMMENT '每人限领张数'", nullable = false)
    private Integer perLimit;

    @Column(name = "min_point", columnDefinition = "decimal(10,2) default '0' COMMENT '使用门槛；0表示无门槛'", nullable = false)
    private BigDecimal minPoint;

    @Column(name = "use_type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '使用类型：0->全场通用；1->指定分类；2->指定商品'", nullable = false)
    private Integer useType;

    @Column(name = "note", columnDefinition = "varchar(64) COMMENT '备注'", nullable = false)
    private String note;

    @Column(name = "publish_count", columnDefinition = "int unsigned COMMENT '发行数量'", nullable = false)
    private Integer publishCount;

    @Column(name = "use_count", columnDefinition = "int unsigned COMMENT '已使用数量'", nullable = false)
    private Integer useCount;

    @Column(name = "receive_count", columnDefinition = "int unsigned COMMENT '领取数量'", nullable = false)
    private Integer receiveCount;


    @Column(name = "member_level", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '可领取的会员类型：0->无限时'", nullable = false)
    private Integer memberLevel;

    @Column(name = "enable_time", columnDefinition = "datetime COMMENT '可以领取的日期'")
    private Date enableTime;
    @Column(name = "start_time", columnDefinition = "datetime COMMENT '开始时间'")
    private Date startTime;
    @Column(name = "end_time", columnDefinition = "datetime COMMENT '结束时间'")
    private Date endTime;

}