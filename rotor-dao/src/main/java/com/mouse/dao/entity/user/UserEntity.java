package com.mouse.dao.entity.user;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * @author ; lidongdong
 * @Description 用户表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_user")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 6498159357192870652L;
    @Id
    @Column(name = "id", columnDefinition = "varchar(32) COMMENT '用户ID'", nullable = false)
    private String id;

    @Column(name = "user_name", columnDefinition = "varchar(32) COMMENT '用户名称'", nullable = false)
    private String userName;

    @Column(name = "password", columnDefinition = "varchar(64) COMMENT '用户密码'", nullable = false)
    private String password;

    @Column(name = "gender", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '性别：0->未知；1->男；2->女'", nullable = false)
    private Integer gender;

    @Column(name = "birthday", columnDefinition = "datetime COMMENT '出生日期'")
    private LocalDate birthday;

    @Column(name = "last_login_time", columnDefinition = "datetime COMMENT '最近一次登录时间'")
    private LocalDateTime lastLoginTime;

    @Column(name = "last_login_ip", columnDefinition = "varchar(32) COMMENT '最近一次登录IP地址'")
    private String lastLoginIp;

    @Column(name = "user_level", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '0 普通用户，1 VIP用户，2 高级VIP用户'", nullable = false)
    private Integer userLevel;

    @Column(name = "nick_name", columnDefinition = "varchar(32) COMMENT '用户昵称或网络名称'", nullable = false)
    private String nickName;

    @Column(name = "mobile", columnDefinition = "varchar(32) COMMENT '用户手机号码'")
    private String mobile;

    @Column(name = "avatar", columnDefinition = "varchar(128) COMMENT '用户头像图片'")
    private String avatar;

    @Column(name = "weixin_openid", columnDefinition = "varchar(32) COMMENT '微信登录openid'")
    private String weixinOpenid;

    @Column(name = "session_key", columnDefinition = "varchar(32) COMMENT '微信登录会话KEY'")
    private String sessionKey;

    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '用户状态'", nullable = false)
    private Integer status;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

    @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除 0 未删除，1 删除'", nullable = false)
    private Boolean deleted;
}