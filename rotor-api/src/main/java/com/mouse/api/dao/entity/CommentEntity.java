package com.mouse.api.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @author ; lidongdong
 * @Description 评论
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_comment")
public class CommentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "type_id", columnDefinition = "int default '0' COMMENT '类型ID'", nullable = false)
    private Integer typeId;
    @Column(name = "session_id", columnDefinition = "int default '0' COMMENT '商品Id'", nullable = false)
    private Integer goodsId;
    @Column(name = "content", columnDefinition = "varchar(60) COMMENT '储存为base64编码'", nullable = false)
    private String content;
    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '状态'", nullable = false)
    private Integer status;
    @Column(name = "user_id", columnDefinition = "int default '0' COMMENT '会员Id'", nullable = false)
    private Integer userId;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
