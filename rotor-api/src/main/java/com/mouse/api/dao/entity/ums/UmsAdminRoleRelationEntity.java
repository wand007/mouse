package com.mouse.api.dao.entity.ums;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 后台用户和角色关系表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "ums_admin_role_relation")
public class UmsAdminRoleRelationEntity implements Serializable {
    private static final long serialVersionUID = -3397305751264280539L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_id", columnDefinition = "bigint(20) unsigned COMMENT '后台用户表ID'", nullable = false)
    private Long adminId;

    @Column(name = "role_id", columnDefinition = "bigint(20) unsigned COMMENT '后台用户角色ID'", nullable = false)
    private Long roleId;

}