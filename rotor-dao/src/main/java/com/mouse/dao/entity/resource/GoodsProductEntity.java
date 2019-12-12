package com.mouse.dao.entity.resource;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 商品货品表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_goods_product")
public class GoodsProductEntity implements Serializable {
    private static final long serialVersionUID = 5456362462146954577L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "goods_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '商品表的商品ID'", nullable = false)
    private Integer goodsId;

    //    @ElementCollection
    @Column(name = "specifications", columnDefinition = "varchar(1023) DEFAULT '[]' COMMENT '商品规格值列表，采用JSON数组格式'", nullable = false)
    private String[] specifications;

    @Column(name = "price", columnDefinition = "decimal(10,2) default '0' COMMENT '商品货品价格'", nullable = false)
    private BigDecimal price;

    @Column(name = "number", columnDefinition = "int unsigned DEFAULT '0' COMMENT '商品货品数量'", nullable = false)
    private Integer number;

    @Column(name = "url", columnDefinition = "varchar(500) COMMENT '商品货品图片'", nullable = false)
    private String url;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

   @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除 0 未删除，1 删除'", nullable = false)
    private Boolean deleted;
}