package com.mouse.api.dao.entity.cms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 帮助分类表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "cms_help_category")
public class CmsHelpCategoryEntity implements Serializable {
    private static final long serialVersionUID = -5683028265584652822L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '分类名称'", nullable = false)
    private String name;
    @Column(name = "icon", columnDefinition = "varchar(64) COMMENT '分类图标'", nullable = false)
    private String icon;
    @Column(name = "help_count", columnDefinition = "int unsigned DEFAULT '0' COMMENT '专题数量'", nullable = false)
    private Integer helpCount;
    @Column(name = "sort", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '排序'", nullable = false)
    private Integer sort;
    @Column(name = "show_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '显示状态：0->不显示；1->显示'", nullable = false)
    private Integer showStatus;



}