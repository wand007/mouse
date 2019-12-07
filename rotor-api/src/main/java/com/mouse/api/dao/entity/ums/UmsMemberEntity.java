package com.mouse.api.dao.entity.ums;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ; lidongdong
 * @Description 会员表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "ums_member")
public class UmsMemberEntity implements Serializable {
    private static final long serialVersionUID = -8916831513557029137L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_level_id", columnDefinition = "bigint(20) unsigned COMMENT '会员等级记录ID'", nullable = false)
    private Long memberLevelId;

    @Column(name = "user_name", columnDefinition = "varchar(32) COMMENT '会员名称'", nullable = false)
    private String userName;

    @Column(name = "password", columnDefinition = "varchar(32) COMMENT '会员密码'", nullable = false)
    private String password;

    @Column(name = "nick_name", columnDefinition = "varchar(32) COMMENT '会员昵称'", nullable = false)
    private String nickname;

    @Column(name = "phone", columnDefinition = "varchar(64) COMMENT '收货人电话'", nullable = false)
    private String phone;

    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '帐号启用状态:0->禁用；1->启用'", nullable = false)
    private Integer status;

    @Column(name = "icon", columnDefinition = "varchar(64) COMMENT '头像'", nullable = false)
    private String icon;

    @Column(name = "gender", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '性别：0->未知；1->男；2->女'", nullable = false)
    private Integer gender;
    @Column(name = "birthday", columnDefinition = "datetime COMMENT '出生日期'")
    private Date birthday;

    @Column(name = "city", columnDefinition = "varchar(32) COMMENT '所做城市'", nullable = false)
    private String city;

    @Column(name = "job", columnDefinition = "varchar(60) COMMENT '职业'", nullable = false)
    private String job;

    @Column(name = "personalized_signature", columnDefinition = "varchar(60) COMMENT '个性签名'", nullable = false)
    private String personalizedSignature;

    @Column(name = "source_type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '用户来源'", nullable = false)
    private Integer sourceType;

    @Column(name = "integration", columnDefinition = "int unsigned COMMENT '积分'", nullable = false)
    private Integer integration;

    @Column(name = "growth", columnDefinition = "int unsigned COMMENT '成长值'", nullable = false)
    private Integer growth;

    @Column(name = "luckey_count", columnDefinition = "int unsigned COMMENT '剩余抽奖次数'", nullable = false)
    private Integer luckeyCount;

    @Column(name = "history_integration", columnDefinition = "int unsigned COMMENT '历史积分数量'", nullable = false)
    private Integer historyIntegration;

}