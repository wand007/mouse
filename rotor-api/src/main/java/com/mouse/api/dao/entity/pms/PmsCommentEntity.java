package com.mouse.api.dao.entity.pms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 商品评价表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "pms_comment")
public class PmsCommentEntity implements Serializable {
    private static final long serialVersionUID = -4905134727111137711L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", columnDefinition = "bigint(20) unsigned COMMENT '商品记录ID'", nullable = false)
    private Long productId;

    @Column(name = "member_nick_name", columnDefinition = "varchar(64) COMMENT '会员昵称'", nullable = false)
    private String memberNickName;

    @Column(name = "product_name", columnDefinition = "varchar(500) COMMENT '商品名称'", nullable = false)
    private String productName;

    @Column(name = "star", columnDefinition = "int unsigned COMMENT '评价星数：0->5'", nullable = false)
    private Integer star;

    @Column(name = "member_ip", columnDefinition = "varchar(500) COMMENT '评价的ip'", nullable = false)
    private String memberIp;

    @Column(name = "pics", columnDefinition = "varchar(500) COMMENT '购买时的商品属性'", nullable = false)
    private String productAttribute;

    @Column(name = "collect_couont", columnDefinition = "int unsigned COMMENT '收藏数量'", nullable = false)
    private Integer collectCouont;

    @Column(name = "read_count", columnDefinition = "int unsigned COMMENT '阅读数量'", nullable = false)
    private Integer readCount;

    @Column(name = "pics", columnDefinition = "varchar(500) COMMENT '上传图片地址，以逗号隔开'", nullable = false)
    private String pics;

    @Column(name = "member_icon", columnDefinition = "varchar(64) COMMENT '评论用户头像'", nullable = false)
    private String memberIcon;

    private Integer replayCount;

    @Column(name = "content", columnDefinition = "text COMMENT '详细内容'", nullable = false)
    private String content;


    @Column(name = "show_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '显示状态：0->不显示；1->显示'", nullable = false)
    private Integer showStatus;

}