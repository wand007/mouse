package com.mouse.dao.entity.sys;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 权限表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_permission")
public class PermissionEntity implements Serializable {
    private static final long serialVersionUID = -487414059107411931L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '角色ID'", nullable = false)
    private Integer roleId;

    @Column(name = "permission", columnDefinition = "varchar(1023) DEFAULT '[]' COMMENT '权限'", nullable = false)
    private String permission;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

   @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除 0 未删除，1 删除'", nullable = false)
    private Boolean deleted;
}