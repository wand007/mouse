package com.mouse.api.dao.entity.ums;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ; lidongdong
 * @Description 后台用户表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "ums_admin")
public class UmsAdminEntity implements Serializable {
    private static final long serialVersionUID = 6043861186679474430L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", columnDefinition = "varchar(32) COMMENT '会员名称'", nullable = false)
    private String userName;

    @Column(name = "password", columnDefinition = "varchar(32) COMMENT '会员密码'", nullable = false)
    private String password;

    @Column(name = "icon", columnDefinition = "varchar(64) COMMENT '头像'", nullable = false)
    private String icon;

    @Column(name = "email", columnDefinition = "varchar(32) COMMENT '邮箱'")
    private String email;

    @Column(name = "nick_name", columnDefinition = "varchar(32) COMMENT '会员昵称'", nullable = false)
    private String nickname;

    @Column(name = "note", columnDefinition = "varchar(64) COMMENT '备注'", nullable = false)
    private String note;

    @Column(name = "login_time", columnDefinition = "datetime COMMENT '最后登录时间'")
    private Date loginTime;

    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用'", nullable = false)
    private Integer status;
}