package com.mouse.api.dao.entity.sys;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ; lidongdong
 * @Description 用户与角色对应关系
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_sys_user_role")
public class SysUserRoleEntity implements Serializable {

    private static final long serialVersionUID = 4451651546092650234L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", columnDefinition = "varchar(32) COMMENT '会员ID'", nullable = false)
    private String userId;

    @Column(name = "role_id", columnDefinition = "int DEFAULT '0' COMMENT '角色ID'", nullable = false)
    private Integer roleId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
}
