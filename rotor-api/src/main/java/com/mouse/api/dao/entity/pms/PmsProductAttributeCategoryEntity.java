package com.mouse.api.dao.entity.pms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 产品属性分类表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "pms_product_attribute_category")
public class PmsProductAttributeCategoryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '产品属性分类名称'", nullable = false)
    private String name;

    @Column(name = "attribute_count", columnDefinition = "int unsigned COMMENT '属性数量'", nullable = false)
    private Integer attributeCount;

    @Column(name = "param_count", columnDefinition = "int unsigned COMMENT '参数数量'", nullable = false)
    private Integer paramCount;

}