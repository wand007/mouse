package com.mouse.api.dao.entity.cms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 专题表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "cms_subject")
public class CmsSubjectEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_id", columnDefinition = "bigint(20) unsigned COMMENT '分类表ID'", nullable = false)
    private Long categoryId;

    @Column(name = "title", columnDefinition = "varchar(100) COMMENT '专题主题'", nullable = false)
    private String title;

    @Column(name = "pic", columnDefinition = "varchar(500) COMMENT '专题主图'", nullable = false)
    private String pic;

    @Column(name = "product_count", columnDefinition = "int unsigned DEFAULT '0' COMMENT '关联产品数量'", nullable = false)
    private Integer productCount;

    @Column(name = "recommend_status", columnDefinition = "int unsigned DEFAULT '0' COMMENT '推荐数量'", nullable = false)
    private Integer recommendStatus;

    @Column(name = "collect_count", columnDefinition = "int unsigned DEFAULT '0' COMMENT '集合数量'", nullable = false)
    private Integer collectCount;

    @Column(name = "read_count", columnDefinition = "int unsigned DEFAULT '0' COMMENT '阅读数量'", nullable = false)
    private Integer readCount;

    @Column(name = "comment_count", columnDefinition = "int unsigned DEFAULT '0' COMMENT '评论数量'", nullable = false)
    private Integer commentCount;

    @Column(name = "album_pics", columnDefinition = "varchar(1000) COMMENT '画册图片用逗号分割'", nullable = false)
    private String albumPics;

    @Column(name = "description", columnDefinition = "varchar(1000) COMMENT '专题描述'", nullable = false)
    private String description;

    @Column(name = "forward_count", columnDefinition = "int unsigned DEFAULT '0' COMMENT '转发数'", nullable = false)
    private Integer forwardCount;

    @Column(name = "category_name", columnDefinition = "varchar(60) COMMENT '专题分类名称'", nullable = false)
    private String categoryName;

    @Column(name = "content", columnDefinition = "text COMMENT '专题分类内容'", nullable = false)
    private String content;

    @Column(name = "show_status", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '显示状态：0->不显示；1->显示'", nullable = false)
    private Integer showStatus;


}