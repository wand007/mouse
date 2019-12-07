package com.mouse.api.dao.entity.ums;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 积分变化历史记录表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "ums_integration_change_history")
public class UmsIntegrationChangeHistoryEntity implements Serializable {
    private static final long serialVersionUID = 8407670595435539252L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", columnDefinition = "bigint(20) unsigned COMMENT '会员ID'", nullable = false)
    private Long memberId;

    @Column(name = "source_type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '积分来源：0->购物；1->管理员修改'", nullable = false)
    private Integer sourceType;

    @Column(name = "change_type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '改变类型：1->增加；-1->减少'", nullable = false)
    private Integer changeType;

    @Column(name = "change_count", columnDefinition = "int unsigned DEFAULT '0' COMMENT '积分改变数量'", nullable = false)
    private Integer changeCount;

    @Column(name = "operate_man", columnDefinition = "varchar(64) COMMENT '操作人'", nullable = false)
    private String operateMan;

    @Column(name = "operate_note", columnDefinition = "varchar(64) COMMENT '操作备注'", nullable = false)
    private String operateNote;


}