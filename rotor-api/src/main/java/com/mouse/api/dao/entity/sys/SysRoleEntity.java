package com.mouse.api.dao.entity.sys;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ; lidongdong
 * @Description 角色
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_sys_role")
public class SysRoleEntity implements Serializable {

    private static final long serialVersionUID = 8126580977846389778L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "role_name", columnDefinition = "varchar(64) COMMENT '角色名称'", nullable = false)
    private String roleName;
    @Column(name = "dept_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '部门ID'", nullable = false)
    private Integer deptId;

    @Column(name = "dept_name", columnDefinition = "varchar(64) COMMENT '部门名称'", nullable = false)
    private String deptName;

    @Column(name = "remarks", columnDefinition = "varchar(64) COMMENT '备注'", nullable = false)
    private String remarks;


    @Column(name = "create_user_id", columnDefinition = "varchar(32) COMMENT '创建者ID'", nullable = false)
    private String createUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
