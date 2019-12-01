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
 * @Description 商品属性
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_goods_attribute")
public class GoodsAttributeEntity implements Serializable {

    private static final long serialVersionUID = -1565031482631292458L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "goods_id", columnDefinition = "int unsigned default '0' COMMENT '商品Id'", nullable = false)
    private Integer goodsId;
    @Column(name = "attribute_id", columnDefinition = "int unsigned default '0' COMMENT '属性Id'", nullable = false)
    private Integer attributeId;
    @Column(name = "value", columnDefinition = "varchar(32) COMMENT '属性值'", nullable = false)
    private String value;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
