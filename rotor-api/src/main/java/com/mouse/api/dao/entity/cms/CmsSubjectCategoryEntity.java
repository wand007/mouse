package com.mouse.api.dao.entity.cms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 专题分类表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "cms_subject_category")
public class CmsSubjectCategoryEntity implements Serializable {
    private static final long serialVersionUID = 4439718368950873864L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '专题分类名称'", nullable = false)
    private String name;

    @Column(name = "icon", columnDefinition = "varchar(64) COMMENT '分类图标'", nullable = false)
    private String icon;

    @Column(name = "subject_count", columnDefinition = "int unsigned DEFAULT '0' COMMENT '专题数量'", nullable = false)
    private Integer subjectCount;

    @Column(name = "sort", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '排序'", nullable = false)
    private Integer sort;
    @Column(name = "show_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '显示状态：0->不显示；1->显示'", nullable = false)
    private Integer showStatus;

}