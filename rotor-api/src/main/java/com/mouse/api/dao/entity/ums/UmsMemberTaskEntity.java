package com.mouse.api.dao.entity.ums;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 会员任务表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "ums_member_task")
public class UmsMemberTaskEntity implements Serializable {
    private static final long serialVersionUID = -48184028944738185L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '会员任务名称'", nullable = false)
    private String name;

    @Column(name = "growth", columnDefinition = "int unsigned COMMENT '赠送成长值'", nullable = false)
    private Integer growth;

    @Column(name = "integration", columnDefinition = "int unsigned COMMENT '赠送积分'", nullable = false)
    private Integer integration;

    @Column(name = "type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '任务类型：0->新手任务；1->日常任务'", nullable = false)
    private Integer type;

}