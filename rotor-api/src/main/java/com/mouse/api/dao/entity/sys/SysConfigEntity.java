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
 * @Description 系统配置信息
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_sys_config")
public class SysConfigEntity implements Serializable {
    private static final long serialVersionUID = 2030455234948903316L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name =  "\"key\"", columnDefinition = "varchar(32) COMMENT 'key'", nullable = false)
    private String key;

    @Column(name =  "\"value\"", columnDefinition = "varchar(32) COMMENT '值'", nullable = false)
    private String value;

    @Column(name = "remark", columnDefinition = "varchar(32) COMMENT '备注'")
    private String remark;

    @Column(name = "is_show", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '状态   0：隐藏   1：显示'", nullable = false)
    private Boolean isShow;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
