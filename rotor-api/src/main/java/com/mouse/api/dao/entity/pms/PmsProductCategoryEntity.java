package com.mouse.api.dao.entity.pms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 产品分类
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "pms_product_category")
public class PmsProductCategoryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_id", columnDefinition = "bigint(20) unsigned COMMENT '上级分类的编号：0表示一级分类'", nullable = false)
    private Long parentId;

    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '产品分类名称'", nullable = false)
    private String name;

    @Column(name = "level", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '分类级别：0->1级；1->2级'", nullable = false)
    private Integer level;

    @Column(name = "product_count", columnDefinition = "int unsigned DEFAULT '0' COMMENT '关联产品数量'", nullable = false)
    private Integer productCount;

    @Column(name = "product_unit", columnDefinition = "varchar(60) COMMENT '商品单位'", nullable = false)
    private String productUnit;

    @Column(name = "nav_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否显示在导航栏：0->不显示；1->显示'", nullable = false)
    private Integer navStatus;

    @Column(name = "icon", columnDefinition = "varchar(64) COMMENT '图标'", nullable = false)
    private String icon;

    @Column(name = "keywords", columnDefinition = "varchar(32) COMMENT '关键字'", nullable = false)
    private String keywords;

    @Column(name = "description", columnDefinition = "varchar(60) COMMENT '描述'", nullable = false)
    private String description;

    @Column(name = "sort", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '排序'", nullable = false)
    private Integer sort;

    @Column(name = "show_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '显示状态：0->不显示；1->显示'", nullable = false)
    private Integer showStatus;
}