package com.mouse.api.dao.entity.ums;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 后台用户角色和权限关系表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "ums_role_permission_relation")
public class UmsRolePermissionRelationEntity implements Serializable {
    private static final long serialVersionUID = -4110385885418508064L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_id", columnDefinition = "bigint(20) unsigned COMMENT '后台用户角色ID'", nullable = false)
    private Long roleId;

    @Column(name = "permission_id", columnDefinition = "bigint(20) unsigned COMMENT '权限关系表ID'", nullable = false)
    private Long permissionId;

}