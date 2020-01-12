package com.mouse.dao.entity.operate;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author ; lidongdong
 * @Description 优惠券信息及规则表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_coupon")
public class CouponEntity implements Serializable {
    private static final long serialVersionUID = 2198251200656824945L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "\"name\"", columnDefinition = "varchar(64) COMMENT '优惠券名称'", nullable = false)
    private String name;

    @Column(name = "\"desc\"", columnDefinition = "varchar(127) COMMENT '优惠券介绍，通常是显示优惠券使用限制文字'", nullable = false)
    private String desc;

    @Column(name = "tag", columnDefinition = "varchar(64) COMMENT '优惠券标签，例如新人专用'", nullable = false)
    private String tag;

    @Column(name = "total", columnDefinition = "int unsigned DEFAULT '0' COMMENT '优惠券数量，如果是0，则是无限量'", nullable = false)
    private Integer total;

    @Column(name = "discount", columnDefinition = "decimal(10,2) default '0' COMMENT '优惠金额'", nullable = false)
    private BigDecimal discount;

    @Column(name = "\"min\"", columnDefinition = "decimal(10,2) default '0' COMMENT '最少消费金额才能使用优惠券'", nullable = false)
    private BigDecimal min;

    @Column(name = "\"limit\"", columnDefinition = "smallint unsigned DEFAULT '0' COMMENT '用户领券限制数量，如果是0，则是不限制；默认是1，限领一张'", nullable = false)
    private Short limit;

    @Column(name = "type", columnDefinition = "smallint unsigned DEFAULT '0' COMMENT '优惠券赠送类型，如果是0则通用券，用户领取；如果是1，则是注册赠券；如果是2，则是优惠券码兑换'", nullable = false)
    private Short type;

    @Column(name = "\"status\"", columnDefinition = "smallint unsigned DEFAULT '0' COMMENT '优惠券状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架'", nullable = false)
    private Short status;

    @Column(name = "goods_type", columnDefinition = "smallint unsigned DEFAULT '0' COMMENT '商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制'", nullable = false)
    private Short goodsType;

    @Column(name = "goods_value", columnDefinition = "varchar(1023) DEFAULT '[]' COMMENT '商品限制值，goods_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合'", nullable = false)
    private String goodsValue;

    @Column(name = "\"code\"", columnDefinition = "varchar(64) COMMENT '优惠券兑换码'", nullable = false)
    private String code;

    @Column(name = "time_type", columnDefinition = "smallint unsigned DEFAULT '0' COMMENT '有效时间限制，如果是0，则基于领取时间的有效天数days；如果是1，则start_time和end_time是优惠券有效期'", nullable = false)
    private Short timeType;

    @Column(name = "days", columnDefinition = "smallint unsigned DEFAULT '0' COMMENT '基于领取时间的有效天数days'", nullable = false)
    private Short days;

    @Column(name = "start_time", columnDefinition = "datetime COMMENT '使用券开始时间'", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", columnDefinition = "datetime COMMENT '使用券截至时间'", nullable = false)
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