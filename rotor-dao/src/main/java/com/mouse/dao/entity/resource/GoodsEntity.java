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
 * @Description 商品基本信息表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_goods")
public class GoodsEntity implements Serializable {
    private static final long serialVersionUID = -8634825364859671423L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "goods_sn", columnDefinition = "varchar(255) COMMENT '商品编号'", nullable = false)
    private String goodsSn;

    @Column(name = "\"name\"", columnDefinition = "varchar(255) COMMENT '商品名称'", nullable = false)
    private String name;

    @Column(name = "category_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '商品所属类目ID'", nullable = false)
    private Integer categoryId;

    @Column(name = "brand_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '品牌ID'", nullable = false)
    private Integer brandId;

    @Column(name = "gallery", columnDefinition = "varchar(255) COMMENT '商品宣传图片列表，采用JSON数组格式'", nullable = false)
    private String gallery;

    @Column(name = "keywords", columnDefinition = "varchar(255) COMMENT '商品关键字，采用逗号间隔'", nullable = false)
    private String keywords;

    @Column(name = "brief", columnDefinition = "varchar(255) COMMENT '商品简介'", nullable = false)
    private String brief;

    @Column(name = "is_on_sale", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '是否上架'", nullable = false)
    private Boolean isOnSale;

    @Column(name = "sort_order", columnDefinition = "smallint unsigned DEFAULT '100' COMMENT '排序'", nullable = false)
    private Short sortOrder;

    @Column(name = "pic_url", columnDefinition = "varchar(255) COMMENT '商品页面商品图片'", nullable = false)
    private String picUrl;

    @Column(name = "share_url", columnDefinition = "varchar(255) COMMENT '商品分享朋友圈图片'", nullable = false)
    private String shareUrl;

    @Column(name = "is_new", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否新品首发，如果设置则可以在新品首发页面展示'", nullable = false)
    private Boolean isNew;

    @Column(name = "is_hot", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否人气推荐，如果设置则可以在人气推荐页面展示'", nullable = false)
    private Boolean isHot;

    @Column(name = "unit", columnDefinition = "varchar(255) DEFAULT '’件‘' COMMENT '商品单位，例如件、盒'", nullable = false)
    private String unit;

    @Column(name = "counter_price", columnDefinition = "decimal(10,2) default '0' COMMENT '专柜价格'", nullable = false)
    private BigDecimal counterPrice;

    @Column(name = "retail_price", columnDefinition = "decimal(10,2) default '0' COMMENT '零售价格'", nullable = false)
    private BigDecimal retailPrice;

    @Column(name = "detail", columnDefinition = "text COMMENT '商品详细介绍，是富文本格式'", nullable = false)
    private String detail;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

    @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除 0 未删除，1 删除'", nullable = false)
    private Boolean deleted;

}