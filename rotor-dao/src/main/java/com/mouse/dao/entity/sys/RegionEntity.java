package com.mouse.dao.entity.sys;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 行政区域表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_region")
public class RegionEntity implements Serializable {
    private static final long serialVersionUID = -8366410071746626381L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pid", columnDefinition = "int unsigned DEFAULT '0' COMMENT '行政区域父ID，例如区县的pid指向市，市的pid指向省，省的pid则是0'", nullable = false)
    private Integer pid;

    @Column(name = "\"name\"", columnDefinition = "varchar(64)  COMMENT '行政区域名称'", nullable = false)
    private String name;

    @Column(name = "type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '行政区域类型，如如1则是省， 如果是2则是市，如果是3则是区县'", nullable = false)
    private Integer type;

    @Column(name = "code", columnDefinition = "int unsigned DEFAULT '0' COMMENT '行政区域编码'", nullable = false)
    private Integer code;

}