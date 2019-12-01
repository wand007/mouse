package com.mouse.api.dao.entity.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * @author ; lidongdong
 * @Description 系统日志
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_sys_log")
public class SysLogEntity implements Serializable {

    private static final long serialVersionUID = -1072569117008858265L;
    @Id
    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '部门名称'", nullable = false)
    private String id;
    @Column(name = "user_name", columnDefinition = "varchar(64) COMMENT '用户名'", nullable = false)
    private String userName;
    @Column(name = "operation", columnDefinition = "varchar(64) COMMENT '用户操作'", nullable = false)
    private String operation;
    @Column(name = "method", columnDefinition = "varchar(64) COMMENT '请求方法'", nullable = false)
    private String method;
    @Column(name = "params", columnDefinition = "varchar(64) COMMENT '请求参数'", nullable = false)
    private String params;
    @Column(name = "ip", columnDefinition = "varchar(64) COMMENT 'IP地址'", nullable = false)
    private String ip;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
}
