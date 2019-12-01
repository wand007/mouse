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
 * @Description 角色与菜单对应关系
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_sys_role_menu")
public class SysRoleMenuEntity implements Serializable {

    private static final long serialVersionUID = -7406712030541177751L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '角色ID'", nullable = false)
    private Integer roleId;


    @Column(name = "menu_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '菜单ID'", nullable = false)
    private Integer menuId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;

}
