package com.mouse.api.dao.entity.sms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 优惠券和产品分类关系表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "sms_coupon_product_category_relation")
public class SmsCouponProductCategoryRelationEntity implements Serializable {
    private static final long serialVersionUID = 2612285138526666983L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coupon_id", columnDefinition = "bigint(20) unsigned COMMENT '优惠券ID'", nullable = false)
    private Long couponId;

    @Column(name = "product_category_id", columnDefinition = "bigint(20) unsigned COMMENT '商品分类id'", nullable = false)
    private Long productCategoryId;

    @Column(name = "product_category_name", columnDefinition = "varchar(32) COMMENT '产品分类名称'", nullable = false)
    private String productCategoryName;

    @Column(name = "parent_category_name", columnDefinition = "varchar(32) COMMENT '父分类名称'", nullable = false)
    private String parentCategoryName;

}