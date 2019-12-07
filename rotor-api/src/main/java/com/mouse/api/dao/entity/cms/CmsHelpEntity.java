package com.mouse.api.dao.entity.cms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 帮助表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "cms_help")
public class CmsHelpEntity implements Serializable {
    private static final long serialVersionUID = 3641882689299107419L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category_id", columnDefinition = "bigint(20) unsigned COMMENT '帮助分类表'", nullable = false)
    private Long categoryId;
    @Column(name = "icon", columnDefinition = "varchar(64) COMMENT '帮助图标'", nullable = false)
    private String icon;
    @Column(name = "title", columnDefinition = "varchar(64) COMMENT 'title'", nullable = false)
    private String title;
    @Column(name = "read_count", columnDefinition = "int unsigned DEFAULT '0' COMMENT '阅读次数'", nullable = false)
    private Integer readCount;
    @Column(name = "show_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '显示状态：0->不显示；1->显示'", nullable = false)
    private Integer showStatus;
    @Column(name = "content", columnDefinition = "text COMMENT 'content'", nullable = false)
    private String content;

}