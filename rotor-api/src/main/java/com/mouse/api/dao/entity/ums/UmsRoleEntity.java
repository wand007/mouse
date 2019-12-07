package com.mouse.api.dao.entity.ums;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 后台用户角色表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "ums_role")
public class UmsRoleEntity implements Serializable {
    private static final long serialVersionUID = 3241568088670625294L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '权限名称'", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "varchar(64) COMMENT '描述'", nullable = false)
    private String description;

    @Column(name = "admin_count", columnDefinition = "int unsigned DEFAULT '0' COMMENT '后台用户数量'", nullable = false)
    private Integer adminCount;

    @Column(name = "status", columnDefinition = "int unsigned DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用'", nullable = false)
    private Boolean status;

    @Column(name = "sort", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '排序'", nullable = false)
    private Integer sort;
}