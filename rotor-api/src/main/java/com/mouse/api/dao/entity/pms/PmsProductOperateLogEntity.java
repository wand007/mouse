package com.mouse.api.dao.entity.pms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ; lidongdong
 * @Description 商品操作日志(只针对同商品)
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "pms_product_operate_log")
public class PmsProductOperateLogEntity implements Serializable {
    private static final long serialVersionUID = -2833598504704343390L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", columnDefinition = "bigint(20) unsigned COMMENT '商品记录ID'", nullable = false)
    private Long productId;

    @Column(name = "price_old", columnDefinition = "int unsigned COMMENT '售价（旧）'", nullable = false)
    private BigDecimal priceOld;
    @Column(name = "price_new", columnDefinition = "int unsigned COMMENT '售价（旧）'", nullable = false)
    private BigDecimal priceNew;

    @Column(name = "sale_price_old", columnDefinition = "int unsigned COMMENT '进价（旧）'", nullable = false)
    private BigDecimal salePriceOld;
    @Column(name = "sale_price_new", columnDefinition = "int unsigned COMMENT '进价（新）'", nullable = false)
    private BigDecimal salePriceNew;

    @Column(name = "gift_point_old", columnDefinition = "int unsigned COMMENT '赠送的积分（旧）'", nullable = false)
    private Integer giftPointOld;
    @Column(name = "gift_point_new", columnDefinition = "int unsigned COMMENT '赠送的积分（新）'", nullable = false)
    private Integer giftPointNew;

    @Column(name = "use_point_limit_old", columnDefinition = "int unsigned COMMENT '限制使用的积分数（旧）'", nullable = false)
    private Integer usePointLimitOld;

    @Column(name = "use_point_limit_new", columnDefinition = "int unsigned COMMENT '限制使用的积分数（新）'", nullable = false)
    private Integer usePointLimitNew;

    @Column(name = "operate_man", columnDefinition = "varchar(64) COMMENT '操作人'", nullable = false)
    private String operateMan;

}