package com.mouse.dao.entity.order;

import com.mouse.dao.handler.JsonStringArrayTypeHandler;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 购物车商品表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_cart")
@org.hibernate.annotations.Table(appliesTo = "tbl_cart", comment = "购物车商品表")
public class CartEntity implements Serializable {
    private static final long serialVersionUID = -5851543051804700370L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", columnDefinition = "varchar(32) COMMENT '用户ID'", nullable = false)
    private String userId;

    @Column(name = "goods_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '商品表的商品ID'", nullable = false)
    private Integer goodsId;

    @Column(name = "goods_sn", columnDefinition = "varchar(64) DEFAULT '[]' COMMENT '商品编号'", nullable = false)
    private String goodsSn;

    @Column(name = "goods_name", columnDefinition = "varchar(127) DEFAULT '[]' COMMENT '商品名称'", nullable = false)
    private String goodsName;

    @Column(name = "product_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '商品货品表的货品ID'", nullable = false)
    private Integer productId;

    @Column(name = "price", columnDefinition = "decimal(10,2) default '0' COMMENT '商品货品的价格'", nullable = false)
    private BigDecimal price;

    @Column(name = "number", columnDefinition = "smallint unsigned DEFAULT '0' COMMENT '商品货品的数量'", nullable = false)
    private Integer number;

    @Convert(converter = JsonStringArrayTypeHandler.class)
    @Column(name = "specifications", columnDefinition = "varchar(1023) DEFAULT '[]' COMMENT '商品规格值列表，采用JSON数组格式'", nullable = false)
    private String[] specifications;

    @Column(name = "checked", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '购物车中商品是否选择状态'", nullable = false)
    private Boolean checked;

    @Column(name = "pic_url", columnDefinition = "varchar(255) COMMENT '商品图片或者商品货品图片'", nullable = false)
    private String picUrl;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;


}