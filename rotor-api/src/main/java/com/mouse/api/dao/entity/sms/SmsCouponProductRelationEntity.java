package com.mouse.api.dao.entity.sms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 优惠券和产品的关系表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "sms_coupon_product_relation")
public class SmsCouponProductRelationEntity implements Serializable {
    private static final long serialVersionUID = -4450131075407517571L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coupon_id", columnDefinition = "bigint(20) unsigned COMMENT '优惠券ID'", nullable = false)
    private Long couponId;

    @Column(name = "product_id", columnDefinition = "bigint(20) unsigned COMMENT '商品记录ID'", nullable = false)
    private Long productId;

    @Column(name = "product_name", columnDefinition = "varchar(500) COMMENT '商品名称'", nullable = false)
    private String productName;

    @Column(name = "product_sn", columnDefinition = "varchar(200) COMMENT '商品编码'", nullable = false)
    private String productSn;

}