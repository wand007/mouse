package com.mouse.dao.entity.sys;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 操作日志表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_log")
public class LogEntity implements Serializable {
    private static final long serialVersionUID = -2443072118010755477L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "admin", columnDefinition = "varchar(32) COMMENT '管理员'", nullable = false)
    private String admin;

    @Column(name = "ip", columnDefinition = "varchar(32) COMMENT '管理员地址'", nullable = false)
    private String ip;

    @Column(name = "type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '操作分类'", nullable = false)
    private Integer type;

    @Column(name = "action", columnDefinition = "varchar(45) COMMENT '操作动作'", nullable = false)
    private String action;

    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '操作状态'", nullable = false)
    private Boolean status;

    @Column(name = "result", columnDefinition = "varchar(255) COMMENT '操作结果，或者成功消息，或者失败消息'")
    private String result;

    @Column(name = "comment", columnDefinition = "varchar(255) COMMENT '补充信息'")
    private String comment;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

    @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除'", nullable = false)
    private Boolean deleted;
}