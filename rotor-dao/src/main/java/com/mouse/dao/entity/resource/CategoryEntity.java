package com.mouse.dao.entity.resource;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 类目表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_category")
public class CategoryEntity implements Serializable {
    private static final long serialVersionUID = -6341340968618496591L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "\"name\"", columnDefinition = "varchar(64) COMMENT '类目名称'", nullable = false)
    private String name;

    @Column(name = "keywords", columnDefinition = "varchar(1023) COMMENT '类目关键字，以JSON数组格式'", nullable = false)
    private String keywords;

    @Column(name = "\"desc\"", columnDefinition = "varchar(255) COMMENT '类目广告语介绍'", nullable = false)
    private String desc;

    @Column(name = "pid", columnDefinition = "int unsigned DEFAULT '0' COMMENT '父类目ID'", nullable = false)
    private Integer pid;

    @Column(name = "icon_url", columnDefinition = "varchar(255) COMMENT '类目图标'", nullable = false)
    private String iconUrl;

    @Column(name = "pic_url", columnDefinition = "varchar(255) COMMENT '类目图片'", nullable = false)
    private String picUrl;

    @Column(name = "level", columnDefinition = "varchar(32) COMMENT 'L1'", nullable = false)
    private String level;

    @Column(name = "sort_order", columnDefinition = "tinyint unsigned DEFAULT '50' COMMENT '排序'", nullable = false)
    private Integer sortOrder;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

   @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除 0 未删除，1 删除'", nullable = false)
    private Boolean deleted;

}