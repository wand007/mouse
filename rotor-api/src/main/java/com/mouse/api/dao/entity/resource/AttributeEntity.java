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
 * @Description 属性
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_attribute")
public class AttributeEntity implements Serializable {

    private static final long serialVersionUID = -3096174435472821709L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "attribute_category_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '所属种类'", nullable = false)
    private Integer attributeCategoryId;
    @Column(name = "name", columnDefinition = "varchar(60) COMMENT '名称'", nullable = false)
    private String name;
    @Column(name = "input_type", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '当添加商品时,该属性的添加类别; 0为手功输入;1为选择输入;2为多行文本输入'", nullable = false)
    private Integer inputType;
    @Column(name = "value", columnDefinition = "varchar(60) COMMENT '即选择输入,则attr_name对应的值的取值就是该这字段值'", nullable = false)
    private String value;
    @Column(name = "sort_order", columnDefinition = "tinyint unsigned DEFAULT '50' COMMENT '排序'", nullable = false)
    private Integer sortOrder;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
