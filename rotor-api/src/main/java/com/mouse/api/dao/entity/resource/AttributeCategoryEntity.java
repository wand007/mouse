package com.mouse.api.dao.entity.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @author ; lidongdong
 * @Description 类目属性
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_attribute_category")
public class AttributeCategoryEntity implements Serializable {

    private static final long serialVersionUID = 6199568380421199507L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", columnDefinition = "varchar(60) COMMENT '名称'", nullable = false)
    private String name;
    @Column(name = "enabled", columnDefinition = "tinyint  DEFAULT '1' COMMENT '状态 1开启; 0关闭'", nullable = false)
    private Boolean enabled;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
