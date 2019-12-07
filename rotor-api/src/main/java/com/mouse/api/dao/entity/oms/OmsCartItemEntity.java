package com.mouse.api.dao.entity.oms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ; lidongdong
 * @Description 购物车表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "oms_cart_item")
public class OmsCartItemEntity implements Serializable {
    private static final long serialVersionUID = -5610073576878402021L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", columnDefinition = "bigint(20) unsigned COMMENT '产品记录ID'", nullable = false)
    private Long productId;

    @Column(name = "product_sku_id", columnDefinition = "bigint(20) unsigned COMMENT '产品SKU记录ID'", nullable = false)
    private Long productSkuId;

    @Column(name = "member_id", columnDefinition = "bigint(20) unsigned COMMENT '会员ID'", nullable = false)
    private Long memberId;

    @Column(name = "quantity", columnDefinition = "int unsigned DEFAULT '0' COMMENT '购买数量'", nullable = false)
    private Integer quantity;

    @Column(name = "price", columnDefinition = "decimal(10,2) default '0' COMMENT '添加到购物车的价格'", nullable = false)
    private BigDecimal price;

    @Column(name = "sp1", columnDefinition = "varchar(64) COMMENT '销售属性1'", nullable = false)
    private String sp1;

    @Column(name = "sp2", columnDefinition = "varchar(64) COMMENT '销售属性2'", nullable = false)
    private String sp2;

    @Column(name = "sp3", columnDefinition = "varchar(64) COMMENT '销售属性3'", nullable = false)
    private String sp3;

    @Column(name = "product_pic", columnDefinition = "varchar(64) COMMENT '商品主图'", nullable = false)
    private String productPic;

    @Column(name = "product_name", columnDefinition = "varchar(64) COMMENT '商品名称'", nullable = false)
    private String productName;

    @Column(name = "product_sub_title", columnDefinition = "varchar(64) COMMENT '商品副标题（卖点）'", nullable = false)
    private String productSubTitle;

    @Column(name = "product_sku_code", columnDefinition = "varchar(64) COMMENT '商品sku条码'", nullable = false)
    private String productSkuCode;

    @Column(name = "member_nick_name", columnDefinition = "varchar(64) COMMENT '会员昵称'", nullable = false)
    private String memberNickName;

    @Column(name = "product_category_id", columnDefinition = "bigint(20) unsigned COMMENT '商品分类'", nullable = false)
    private Long productCategoryId;

    @Column(name = "product_brand", columnDefinition = "varchar(200) COMMENT '商品brand'", nullable = false)
    private String productBrand;

    @Column(name = "product_sn", columnDefinition = "varchar(200) COMMENT '商品编码'", nullable = false)
    private String productSn;

    @Column(name = "product_attr", columnDefinition = "varchar(500) COMMENT '商品销售属性:[{'key':'颜色','value':'颜色'},{'key':'容量','value':'4G'}]'", nullable = false)
    private String productAttr;

    @Column(name = "delete_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否删除'", nullable = false)
    private Boolean deleteStatus;

}