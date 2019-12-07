package com.mouse.api.dao.entity.pms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 品牌表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "pms_brand")
public class PmsBrandEntity implements Serializable {
    private static final long serialVersionUID = -3743001568887228189L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '品牌名称'", nullable = false)
    private String name;

    @Column(name = "first_letter", columnDefinition = "varchar(64) COMMENT '首字母'", nullable = false)
    private String firstLetter;

    @Column(name = "product_count", columnDefinition = "int unsigned DEFAULT '0' COMMENT '产品数量'", nullable = false)
    private Integer productCount;

    @Column(name = "product_comment_count", columnDefinition = "int unsigned DEFAULT '0' COMMENT '产品评论数量'", nullable = false)
    private Integer productCommentCount;

    @Column(name = "logo", columnDefinition = "varchar(64) COMMENT '品牌logo'", nullable = false)
    private String logo;

    @Column(name = "big_pic", columnDefinition = "varchar(64) COMMENT '专区大图'", nullable = false)
    private String bigPic;

    @Column(name = "brand_story", columnDefinition = "varchar(64) COMMENT '品牌故事'", nullable = false)
    private String brandStory;

    @Column(name = "factory_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否为品牌制造商：0->不是；1->是'", nullable = false)
    private Integer factoryStatus;

    @Column(name = "sort", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '排序'", nullable = false)
    private Integer sort;
    @Column(name = "show_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '显示状态：0->不显示；1->显示'", nullable = false)
    private Integer showStatus;

}