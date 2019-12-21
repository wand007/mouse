package com.mouse.dao.entity.user;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 管理员表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_admin")
public class AdminEntity implements Serializable {
    private static final long serialVersionUID = 7190420391634391909L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name", columnDefinition = "varchar(64) COMMENT '管理员名称'", nullable = false)
    private String userName;

    @Column(name = "password", columnDefinition = "varchar(64) COMMENT '管理员密码'", nullable = false)
    private String password;

    @Column(name = "last_login_ip", columnDefinition = "varchar(64) COMMENT '最近一次登录时间'", nullable = false)
    private String lastLoginIp;

    @Column(name = "last_login_time", columnDefinition = "datetime COMMENT '最近一次登录时间'", nullable = false)
    private LocalDateTime lastLoginTime;

    @Column(name = "avatar", columnDefinition = "varchar(64) COMMENT '头像图片'", nullable = false)
    private String avatar;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

   @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除 0 未删除，1 删除'", nullable = false)
    private Boolean deleted;

    @Column(name = "role_ids", columnDefinition = "varchar(127) DEFAULT '[]' COMMENT '角色列表'", nullable = false)
    private String roleIds;

}