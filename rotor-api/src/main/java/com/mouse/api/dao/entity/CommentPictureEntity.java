package com.mouse.api.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author ; lidongdong
 * @Description 评价图片实体
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_comment_picture")
public class CommentPictureEntity implements Serializable {

    private static final long serialVersionUID = 1114110503511941406L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "comment_id", columnDefinition = "int default '0' COMMENT '评价Id'", nullable = false)
    private Integer commentId;
    @Column(name = "pic_url", columnDefinition = "varchar(60) COMMENT '评价图片URL'", nullable = false)
    private String picUrl;
    @Column(name = "sort_order", columnDefinition = "tinyint unsigned DEFAULT '50' COMMENT '排序'", nullable = false)
    private Integer sortOrder;

}
