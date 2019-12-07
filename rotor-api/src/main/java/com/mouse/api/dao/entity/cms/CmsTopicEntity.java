package com.mouse.api.dao.entity.cms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ; lidongdong
 * @Description 话题表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "cms_topic")
public class CmsTopicEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_id", columnDefinition = "bigint(20) unsigned COMMENT '话题表分类表'", nullable = false)
    private Long categoryId;

    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '话题名称'", nullable = false)
    private String name;

    @Column(name = "start_time", columnDefinition = "datetime COMMENT '开始时间'", nullable = false)
    private Date startTime;

    @Column(name = "end_time", columnDefinition = "datetime COMMENT '结束时间'", nullable = false)
    private Date endTime;

    @Column(name = "attend_count", columnDefinition = "int unsigned COMMENT '参与人数'", nullable = false)
    private Integer attendCount;

    @Column(name = "attention_count", columnDefinition = "int unsigned COMMENT '关注人数'", nullable = false)
    private Integer attentionCount;

    @Column(name = "read_count", columnDefinition = "int unsigned COMMENT '阅读数量'", nullable = false)
    private Integer readCount;

    @Column(name = "award_name", columnDefinition = "varchar(64) COMMENT '奖品名称'", nullable = false)
    private String awardName;

    @Column(name = "attend_type", columnDefinition = "varchar(64) COMMENT '参与方式'", nullable = false)
    private String attendType;

    @Column(name = "content", columnDefinition = "text COMMENT '话题内容'", nullable = false)
    private String content;

}