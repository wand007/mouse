package com.mouse.dao.entity.sys;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 关键字表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_keyword")
public class KeywordEntity implements Serializable {
    private static final long serialVersionUID = -2443072118010755477L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "keyword", columnDefinition = "varchar(32) COMMENT '关键字'", nullable = false)
    private String keyword;

    @Column(name = "url", columnDefinition = "varchar(255) COMMENT '关键字的跳转链接'", nullable = false)
    private String url;

    @Column(name = "is_hot", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否是热门关键字'", nullable = false)
    private Boolean isHot;

    @Column(name = "is_default", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否是默认关键字'", nullable = false)
    private Boolean isDefault;

    @Column(name = "sort_order", columnDefinition = "tinyint unsigned DEFAULT '100' COMMENT '排序'", nullable = false)
    private Integer sortOrder;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

    @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除'", nullable = false)
    private Boolean deleted;
}