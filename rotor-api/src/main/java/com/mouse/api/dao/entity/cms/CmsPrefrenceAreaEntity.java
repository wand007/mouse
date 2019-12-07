package com.mouse.api.dao.entity.cms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 优选专区
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "cms_help_category")
public class CmsPrefrenceAreaEntity implements Serializable {
    private static final long serialVersionUID = 7153226910027479711L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '名称'", nullable = false)
    private String name;
    @Column(name = "sub_title", columnDefinition = "varchar(64) COMMENT '副标题'", nullable = false)
    private String subTitle;

    @Column(name = "sort", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '排序'", nullable = false)
    private Integer sort;
    @Column(name = "show_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '显示状态：0->不显示；1->显示'", nullable = false)
    private Integer showStatus;
}