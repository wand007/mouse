package com.mouse.api.dao.entity.ums;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 后台用户和权限关系表(除角色中定义的权限以外的加减权限)
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "ums_admin_permission_relation")
public class UmsAdminPermissionRelationEntity implements Serializable {
    private static final long serialVersionUID = 7206483483595587206L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_id", columnDefinition = "bigint(20) unsigned COMMENT '后台用户表ID'", nullable = false)
    private Long adminId;

    @Column(name = "permission_id", columnDefinition = "bigint(20) unsigned COMMENT '权限关系表ID'", nullable = false)
    private Long permissionId;

    @Column(name = "type", columnDefinition = "tinyint unsigned DEFAULT '1' ", nullable = false)
    private Integer type;

}