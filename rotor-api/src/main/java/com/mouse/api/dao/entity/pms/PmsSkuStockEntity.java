package com.mouse.api.dao.entity.pms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ; lidongdong
 * @Description sku的库存
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "pms_sku_stock")
public class PmsSkuStockEntity implements Serializable {
    private static final long serialVersionUID = 2117208979815801356L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", columnDefinition = "bigint(20) unsigned COMMENT '商品记录ID'", nullable = false)
    private Long productId;

    @Column(name = "sku_code", columnDefinition = "varchar(64) COMMENT 'sku编码'", nullable = false)
    private String skuCode;

    @Column(name = "price", columnDefinition = "decimal(10,2) default '0' COMMENT '单品销售单价'", nullable = false)
    private BigDecimal price;

    @Column(name = "promotion_price", columnDefinition = "decimal(10,2) default '0' COMMENT '单品促销价格'", nullable = false)
    private BigDecimal promotionPrice;

    @Column(name = "lock_stock", columnDefinition = "int unsigned DEFAULT '0' COMMENT '锁定库存'", nullable = false)
    private Integer lockStock;

    @Column(name = "stock", columnDefinition = "int unsigned COMMENT '库存'", nullable = false)
    private Integer stock;

    @Column(name = "low_stock", columnDefinition = "int unsigned COMMENT '库存预警值'", nullable = false)
    private Integer lowStock;

    @Column(name = "sale", columnDefinition = "int unsigned DEFAULT '0' COMMENT '销量'", nullable = false)
    private Integer sale;

    @Column(name = "sp1", columnDefinition = "varchar(64) COMMENT '销售属性1'", nullable = false)
    private String sp1;

    @Column(name = "sp2", columnDefinition = "varchar(64) COMMENT '销售属性2'", nullable = false)
    private String sp2;

    @Column(name = "sp3", columnDefinition = "varchar(64) COMMENT '销售属性3'", nullable = false)
    private String sp3;

    @Column(name = "pic", columnDefinition = "varchar(500) COMMENT '展示图片'", nullable = false)
    private String pic;


}