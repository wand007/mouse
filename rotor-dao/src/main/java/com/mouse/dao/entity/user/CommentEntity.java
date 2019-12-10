package com.mouse.dao.entity.user;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 评论表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_comment")
public class CommentEntity implements Serializable {
    private static final long serialVersionUID = 9204585152696441041L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '如果type=0，则是商品评论；如果是type=1，则是专题评论'", nullable = false)
    private Integer valueId;

    @Column(name = "type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '评论类型，如果type=0，则是商品评论；如果是type=1，则是专题评论；如果type=3，则是订单商品评论'", nullable = false)
    private Integer type;

    @Column(name = "content", columnDefinition = "varchar(1023) COMMENT '评论内容'", nullable = false)
    private String content;

    @Column(name = "user_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '用户表的用户ID'", nullable = false)
    private Integer userId;

    @Column(name = "has_picture", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否含有图片'", nullable = false)
    private Boolean hasPicture;

    //    @ElementCollection
    @Column(name = "pic_urls", columnDefinition = "varchar(1023) COMMENT '图片地址列表，采用JSON数组格式'", nullable = false)
    private String[] picUrls;

    @Column(name = "star", columnDefinition = "smallint unsigned DEFAULT '1' COMMENT '评分， 1-5'", nullable = false)
    private Short star;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

    @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除'", nullable = false)
    private Boolean deleted;
}