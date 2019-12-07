package com.mouse.api.dao.entity.pms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 产品评价回复表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "pms_comment_replay")
public class PmsCommentReplayEntity implements Serializable {
    private static final long serialVersionUID = -8496213925716836112L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment_id", columnDefinition = "bigint(20) unsigned COMMENT '商品评价表ID'", nullable = false)
    private Long commentId;

    @Column(name = "member_nick_name", columnDefinition = "varchar(64) COMMENT '会员昵称'", nullable = false)
    private String memberNickName;

    @Column(name = "member_icon", columnDefinition = "varchar(64) COMMENT '会员头像'", nullable = false)
    private String memberIcon;

    @Column(name = "content", columnDefinition = "text COMMENT '详细内容'", nullable = false)
    private String content;

    @Column(name = "type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '评论人员类型；0->会员；1->管理员'", nullable = false)
    private Integer type;

}