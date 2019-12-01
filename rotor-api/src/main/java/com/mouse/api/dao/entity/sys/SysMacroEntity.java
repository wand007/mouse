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
 * @Description 通用字典表实体
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_sys_macro")
public class SysMacroEntity implements Serializable {

    private static final long serialVersionUID = 7509935720650303333L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "parent_id", columnDefinition = "int DEFAULT '0' COMMENT '父级id,0表示没有父级'", nullable = false)
    private Integer parentId;
    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '名称'", nullable = false)
    private String name;
    @Column(name = "value", columnDefinition = "varchar(64) COMMENT '值'", nullable = false)
    private String value;
    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '状态，0：隐藏   1：显示'", nullable = false)
    private Integer status;
    @Column(name = "type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '类型,0:目录，1:参数配置'", nullable = false)
    private Integer type;
    @Column(name = "sort_order", columnDefinition = "tinyint unsigned DEFAULT '50' COMMENT '排序'", nullable = false)
    private Integer sortOrder;
    @Column(name = "remark", columnDefinition = "varchar(64) COMMENT '备注'", nullable = false)
    private String remark;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
