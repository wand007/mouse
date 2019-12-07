package com.mouse.api.dao.entity.ums;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 后台用户登录日志表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "ums_admin_login_log")
public class UmsAdminLoginLogEntity implements Serializable {
    private static final long serialVersionUID = -7996270474081698134L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_id", columnDefinition = "bigint(20) unsigned COMMENT '后台用户表ID'", nullable = false)
    private Long adminId;

    @Column(name = "ip", columnDefinition = "varchar(64) COMMENT 'ip'", nullable = false)
    private String ip;

    @Column(name = "address", columnDefinition = "varchar(64) ", nullable = false)
    private String address;

    @Column(name = "user_agent", columnDefinition = "varchar(64) COMMENT '浏览器登录类型'", nullable = false)
    private String userAgent;

}