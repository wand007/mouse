package com.mouse.dao.entity.resource;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 商品参数表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_goods_attribute")
public class GoodsAttributeEntity implements Serializable {
    private static final long serialVersionUID = -6878282896095790628L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "goods_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '商品表的商品ID'", nullable = false)
    private Integer goodsId;

    @Column(name = "attribute", columnDefinition = "varchar(255) COMMENT '商品参数名称'", nullable = false)
    private String attribute;

    @Column(name = "value", columnDefinition = "varchar(255) COMMENT '商品参数值'", nullable = false)
    private String value;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

   @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除 0 未删除，1 删除'", nullable = false)
    private Boolean deleted;
}