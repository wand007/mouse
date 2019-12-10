package com.mouse.dao.entity.sys;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 搜索历史表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_search_history")
public class SearchHistoryEntity implements Serializable {
    private static final long serialVersionUID = 3546764213998570120L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '用户表的用户ID'", nullable = false)
    private Integer userId;

    @Column(name = "keyword", columnDefinition = "varchar(32) COMMENT '关键字'", nullable = false)
    private String keyword;

    @Column(name = "\"from\"", columnDefinition = "varchar(32) COMMENT '搜索来源，如pc、wx、app'", nullable = false)
    private String from;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

    @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除'", nullable = false)
    private Boolean deleted;
}