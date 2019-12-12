package com.mouse.dao.entity.sys;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 角色表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_role")
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = -8928561026849059307L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "\"name\"", columnDefinition = "varchar(64) COMMENT '角色名称'", nullable = false)
    private String name;

    @Column(name = "\"desc\"", columnDefinition = "varchar(64) COMMENT '角色描述'", nullable = false)
    private String desc;

    @Column(name = "enabled", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '是否启用'", nullable = false)
    private Boolean enabled;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

   @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除 0 未删除，1 删除'", nullable = false)
    private Boolean deleted;
}