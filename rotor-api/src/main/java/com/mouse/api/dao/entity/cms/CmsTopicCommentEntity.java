package com.mouse.api.dao.entity.cms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 专题评论表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "cms_topic_comment")
public class CmsTopicCommentEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic_id", columnDefinition = "bigint(20) unsigned COMMENT '话题表ID'", nullable = false)
    private Long topicId;

    @Column(name = "member_nick_name", columnDefinition = "varchar(64) COMMENT '会员名称'", nullable = false)
    private String memberNickName;

    @Column(name = "member_icon", columnDefinition = "varchar(64) COMMENT '会员头像'", nullable = false)
    private String memberIcon;

    @Column(name = "content", columnDefinition = "text COMMENT '评论内容'", nullable = false)
    private String content;

    @Column(name = "show_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '显示状态：0->不显示；1->显示'", nullable = false)
    private Integer showStatus;

}