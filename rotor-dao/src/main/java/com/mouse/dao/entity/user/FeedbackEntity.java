package com.mouse.dao.entity.user;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 意见反馈表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_feedback")
public class FeedbackEntity implements Serializable {
    private static final long serialVersionUID = 3332281204516670636L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '用户表的用户ID'", nullable = false)
    private Integer userId;

    @Column(name = "user_name", columnDefinition = "varchar(64) COMMENT '用户名称'", nullable = false)
    private String userName;

    @Column(name = "mobile", columnDefinition = "varchar(32) COMMENT '手机号'", nullable = false)
    private String mobile;

    @Column(name = "feed_type", columnDefinition = "varchar(64) COMMENT '反馈类型'", nullable = false)
    private String feedType;

    @Column(name = "content", columnDefinition = "varchar(1023) COMMENT '反馈内容'", nullable = false)
    private String content;

    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '状态'", nullable = false)
    private Integer status;

    @Column(name = "has_picture", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否含有图片'", nullable = false)
    private Boolean hasPicture;

    //    @ElementCollection
    @Column(name = "pic_urls", columnDefinition = "varchar(1023) COMMENT '图片地址列表，采用JSON数组格式'", nullable = false)
    private String[] picUrls;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

    @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除'", nullable = false)
    private Boolean deleted;
}