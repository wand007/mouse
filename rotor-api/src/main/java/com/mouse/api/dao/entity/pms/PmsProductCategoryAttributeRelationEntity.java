package com.mouse.api.dao.entity.pms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 产品的分类和属性的关系表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "pms_product_category_attribute_relation")
public class PmsProductCategoryAttributeRelationEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_category_id", columnDefinition = "bigint(20) unsigned COMMENT '商品分类id'", nullable = false)
    private Long productCategoryId;

    @Column(name = "product_attribute_id", columnDefinition = "bigint(20) unsigned COMMENT '商品属性记录ID'", nullable = false)
    private Long productAttributeId;
}