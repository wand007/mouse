package com.mouse.api.dao.entity.oms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ; lidongdong
 * @Description 订单中所包含的商品
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "oms_order_item")
public class OmsOrderItemEntity implements Serializable {
    private static final long serialVersionUID = -5338426685517650918L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", columnDefinition = "bigint(20) unsigned COMMENT '订单id'", nullable = false)
    private Long orderId;

    @Column(name = "order_sn", columnDefinition = "varchar(200) COMMENT '订单编号'", nullable = false)
    private String orderSn;

    @Column(name = "product_id", columnDefinition = "bigint(20) unsigned COMMENT '商品记录ID'", nullable = false)
    private Long productId;

    @Column(name = "product_name", columnDefinition = "varchar(500) COMMENT '商品名称'", nullable = false)
    private String productName;

    @Column(name = "product_pic", columnDefinition = "varchar(500) COMMENT '商品图片'", nullable = false)
    private String productPic;

    @Column(name = "product_brand", columnDefinition = "varchar(200) COMMENT '商品品牌'", nullable = false)
    private String productBrand;

    @Column(name = "product_sn", columnDefinition = "varchar(200) COMMENT '商品编码'", nullable = false)
    private String productSn;


    @Column(name = "product_price", columnDefinition = "decimal(10,2) default '0' COMMENT '销售价格'", nullable = false)
    private BigDecimal productPrice;

    @Column(name = "product_quantity", columnDefinition = "int unsigned COMMENT '购买数量'", nullable = false)
    private Integer productQuantity;

    @Column(name = "product_sku_id", columnDefinition = "bigint(20) unsigned COMMENT '商品sku编号'", nullable = false)
    private Long productSkuId;

    @Column(name = "product_sku_code", columnDefinition = "varchar(200) COMMENT '商品sku条码'", nullable = false)
    private String productSkuCode;

    @Column(name = "product_category_id", columnDefinition = "bigint(20) unsigned COMMENT '商品分类id'", nullable = false)
    private Long productCategoryId;

    @Column(name = "sp1", columnDefinition = "varchar(64) COMMENT '销售属性1'", nullable = false)
    private String sp1;

    @Column(name = "sp2", columnDefinition = "varchar(64) COMMENT '销售属性2'", nullable = false)
    private String sp2;

    @Column(name = "sp3", columnDefinition = "varchar(64) COMMENT '销售属性3'", nullable = false)
    private String sp3;


    @Column(name = "promotion_name", columnDefinition = "varchar(200) COMMENT '商品促销名称'", nullable = false)
    private String promotionName;

    @Column(name = "promotion_amount", columnDefinition = "decimal(10,2) default '0' COMMENT '商品促销分解金额'", nullable = false)
    private BigDecimal promotionAmount;

    @Column(name = "coupon_amount", columnDefinition = "decimal(10,2) default '0' COMMENT '优惠券优惠分解金额'", nullable = false)
    private BigDecimal couponAmount;

    @Column(name = "integration_amount", columnDefinition = "decimal(10,2) default '0' COMMENT '积分优惠分解金额'", nullable = false)
    private BigDecimal integrationAmount;

    @Column(name = "real_amount", columnDefinition = "decimal(10,2) default '0' COMMENT '该商品经过优惠后的分解金额'", nullable = false)
    private BigDecimal realAmount;

    @Column(name = "gift_integration", columnDefinition = "int unsigned COMMENT '赠送的积分'", nullable = false)
    private Integer giftIntegration;

    @Column(name = "gift_growth", columnDefinition = "int unsigned COMMENT '赠送的成长值'", nullable = false)
    private Integer giftGrowth;

    @Column(name = "product_attr", columnDefinition = "varchar(500) COMMENT '商品销售属性:[{'key':'颜色','value':'颜色'},{'key':'容量','value':'4G'}]'", nullable = false)
    private String productAttr;

}