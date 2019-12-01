package com.mouse.api.dao.entity.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ; lidongdong
 * @Description 系统用户
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_sys_user")
public class SysUserEntity implements Serializable {

    private static final long serialVersionUID = -7499190643278794628L;
    @Id
    @Column(name = "user_id", columnDefinition = "varchar(32) COMMENT '系统用户ID'", nullable = false)
    private String userId;

    @Column(name = "user_name", columnDefinition = "varchar(32) COMMENT '用户名称'", nullable = false)
    private String username;

    @Column(name = "password", columnDefinition = "varchar(32) COMMENT '用户密码'", nullable = false)
    private transient String password;

    @Column(name = "email", columnDefinition = "varchar(32) COMMENT '邮箱'", nullable = false)
    private String email;

    @Column(name = "mobile", columnDefinition = "varchar(32) COMMENT '手机号'", nullable = false)
    private String mobile;

    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '状态  0：禁用   1：正常'", nullable = false)
    private Integer status;

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

    @Column(name = "dept_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '部门ID'", nullable = false)
    private Integer deptId;
    @Column(name = "merchant_id", columnDefinition = "varchar(32) COMMENT '供应商ID'", nullable = false)
    private String merchantId;
    @Column(name = "merchant_name", columnDefinition = "varchar(32) COMMENT '商户名称'", nullable = false)
    private String merchantName;
    @Column(name = "dept_name", columnDefinition = "varchar(32) COMMENT '部门名称'", nullable = false)
    private String deptName;
    @Column(name = "fx1", columnDefinition = "decimal(10,2) default '0' COMMENT '一级分佣'", nullable = false)
    private BigDecimal fx1;
    @Column(name = "fx2", columnDefinition = "decimal(10,2) default '0' COMMENT '二级分佣'", nullable = false)
    private BigDecimal fx2;
    @Column(name = "fx", columnDefinition = "decimal(10,2) default '0' COMMENT '本人分佣'", nullable = false)
    private BigDecimal fx;
    @Column(name = "pfx", columnDefinition = "decimal(10,2) default '0' COMMENT '平台分佣'", nullable = false)
    private BigDecimal pfx;
    @Column(name = "all_show", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否显示其他商户 0:不显示，1:显示'", nullable = false)
    private Boolean allShow;

}
