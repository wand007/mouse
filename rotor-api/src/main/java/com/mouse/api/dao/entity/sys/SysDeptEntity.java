package com.mouse.api.dao.entity.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * @author ; lidongdong
 * @Description 部门管理
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_sys_dept")
public class SysDeptEntity implements Serializable {

    private static final long serialVersionUID = 2493436745434136343L;
    /**
     * 部门ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deptId;
    @Column(name = "parent_id", columnDefinition = "int DEFAULT '0' COMMENT '上级部门ID，一级部门为0'", nullable = false)
    private Integer parentId;
    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '部门名称'", nullable = false)
    private String name;
    @Column(name = "parent_name", columnDefinition = "varchar(64) COMMENT '上级部门名称'", nullable = false)
    private String parentName;
    @Column(name = "sort_order", columnDefinition = "tinyint unsigned DEFAULT '50' COMMENT '排序'", nullable = false)
    private Integer sortOrder;
    @Column(name = "is_delete", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '删除状态  1：已删除  0：正常'", nullable = false)
    private Boolean isDelete;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
