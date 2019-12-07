package com.mouse.api.dao.entity.pms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 存储产品参数信息的表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "pms_product_attribute_value")
public class PmsProductAttributeValueEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", columnDefinition = "bigint(20) unsigned COMMENT '商品记录ID'", nullable = false)
    private Long productId;

    @Column(name = "product_attribute_id", columnDefinition = "bigint(20) unsigned COMMENT '商品属性记录ID'", nullable = false)
    private Long productAttributeId;

    @Column(name = "value", columnDefinition = "varchar(64) COMMENT '手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开'", nullable = false)
    private String value;

}