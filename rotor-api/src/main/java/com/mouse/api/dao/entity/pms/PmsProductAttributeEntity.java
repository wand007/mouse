package com.mouse.api.dao.entity.pms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 商品属性参数表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "pms_product_attribute")
public class PmsProductAttributeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_attribute_category_id", columnDefinition = "bigint(20) unsigned COMMENT '产品属性分类表ID'", nullable = false)
    private Long productAttributeCategoryId;
    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '商品属性名称'", nullable = false)
    private String name;

    @Column(name = "select_type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '属性选择类型：0->唯一；1->单选；2->多选'", nullable = false)
    private Integer selectType;

    @Column(name = "input_type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '属性录入方式：0->手工录入；1->从列表中选取'", nullable = false)
    private Integer inputType;

    @Column(name = "input_list", columnDefinition = "varchar(64) COMMENT '可选值列表，以逗号隔开'", nullable = false)
    private String inputList;

    @Column(name = "sort", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '排序'", nullable = false)
    private Integer sort;

    @Column(name = "filter_type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '分类筛选样式：1->普通；1->颜色'", nullable = false)
    private Integer filterType;

    @Column(name = "search_type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '检索类型；0->不需要进行检索；1->关键字检索；2->范围检索'", nullable = false)
    private Integer searchType;

    @Column(name = "related_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '相同属性产品是否关联；0->不关联；1->关联'", nullable = false)
    private Integer relatedStatus;

    @Column(name = "hand_add_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否支持手动新增；0->不支持；1->支持'", nullable = false)
    private Integer handAddStatus;

    @Column(name = "type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '属性的类型；0->规格；1->参数'", nullable = false)
    private Integer type;

}