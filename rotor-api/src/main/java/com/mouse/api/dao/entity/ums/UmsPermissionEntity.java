package com.mouse.api.dao.entity.ums;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 后台用户权限表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "ums_permission")
public class UmsPermissionEntity implements Serializable {
    private static final long serialVersionUID = 2395284012994356699L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pid", columnDefinition = "bigint(20) unsigned COMMENT '父级权限ID'", nullable = false)
    private Long pid;

    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '权限名称'", nullable = false)
    private String name;

    @Column(name = "value", columnDefinition = "varchar(255) COMMENT '权限值'", nullable = false)
    private String value;

    @Column(name = "icon", columnDefinition = "varchar(64) COMMENT '菜单图标'", nullable = false)
    private String icon;

    @Column(name = "type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）'", nullable = false)
    private Integer type;

    @Column(name = "uri", columnDefinition = "varchar(64) COMMENT '前端资源路径'", nullable = false)
    private String uri;

    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '启用状态；0->禁用；1->启用'", nullable = false)
    private Integer status;

    @Column(name = "sort", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '排序'", nullable = false)
    private Integer sort;

}