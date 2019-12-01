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
 * @Description 系统菜单实体
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_sys_menu")
public class SysMenuEntity extends Tree<SysMenuEntity> implements Serializable {

    private static final long serialVersionUID = -8471593229625460121L;
    /**
     * 菜单ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuId;

    @Column(name = "parent_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '父菜单ID，一级菜单为0'", nullable = false)
    private Integer parentId;

    @Column(name = "parent_name", columnDefinition = "varchar(64) COMMENT '父菜单名称'", nullable = false)
    private String parentName;

    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '菜单名称'", nullable = false)
    private String name;

    @Column(name = "url", columnDefinition = "varchar(64) COMMENT '菜单URL'", nullable = false)
    private String url;

    @Column(name = "perms", columnDefinition = "varchar(64) COMMENT '授权(多个用逗号分隔，如：user:list,user:create)'", nullable = false)
    private String perms;

    @Column(name = "type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '类型     0：目录   1：菜单   2：按钮'", nullable = false)
    private Integer type;

    @Column(name = "icon", columnDefinition = "varchar(64) COMMENT '菜单图标'", nullable = false)
    private String icon;

    @Column(name = "sort_order", columnDefinition = "tinyint unsigned DEFAULT '50' COMMENT '排序'", nullable = false)
    private Integer sortOrder;

    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '状态，0：隐藏   1：显示'", nullable = false)
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
