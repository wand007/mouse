package com.mouse.api.dao.entity.sms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 首页推荐专题表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "sms_home_recommend_subject")
public class SmsHomeRecommendSubjectEntity implements Serializable {
    private static final long serialVersionUID = -7987697429601466828L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "subject_id", columnDefinition = "bigint(20) unsigned COMMENT '专题表ID'", nullable = false)
    private Long subjectId;

    @Column(name = "subject_name", columnDefinition = "varchar(64) COMMENT '专题名称'", nullable = false)
    private String subjectName;

    @Column(name = "recommend_status", columnDefinition = "int unsigned DEFAULT '0' COMMENT '推荐数量'", nullable = false)
    private Integer recommendStatus;

    @Column(name = "sort", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '排序'", nullable = false)
    private Integer sort;
}