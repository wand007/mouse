package com.mouse.api.dao.entity.sys;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author ; lidongdong
 * @Description 快递公司
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_sys_shipping")
public class SysShippingEntity implements Serializable {

    private static final long serialVersionUID = -7050758940579405600L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "code", columnDefinition = "varchar(32) COMMENT '快递公司代码'", nullable = false)
    private String code;
    @Column(name = "name", columnDefinition = "varchar(32) COMMENT '快递公司名称'", nullable = false)
    private String name;
    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '状态 0正常 1删除'", nullable = false)
    private Integer status;
}
