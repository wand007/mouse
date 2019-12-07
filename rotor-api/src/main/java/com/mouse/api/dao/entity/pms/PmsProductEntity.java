package com.mouse.api.dao.entity.pms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ; lidongdong
 * @Description 商品信息
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "pms_product")
public class PmsProductEntity implements Serializable {
    private static final long serialVersionUID = -3803979815217367702L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "brand_id", columnDefinition = "bigint(20) unsigned COMMENT '品牌记录ID'", nullable = false)
    private Long brandId;
    @Column(name = "product_category_id", columnDefinition = "bigint(20) unsigned COMMENT '商品分类id'", nullable = false)
    private Long productCategoryId;
    @Column(name = "feight_template_id", columnDefinition = "bigint(20) unsigned COMMENT '运费模版ID'", nullable = false)
    private Long feightTemplateId;
    @Column(name = "product_attribute_category_id", columnDefinition = "bigint(20) unsigned COMMENT '产品属性分类表ID'", nullable = false)
    private Long productAttributeCategoryId;
    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '商品名称'", nullable = false)
    private String name;
    @Column(name = "pic", columnDefinition = "varchar(64) COMMENT '展示图片'", nullable = false)
    private String pic;

    @Column(name = "product_sn", columnDefinition = "varchar(64) COMMENT '货号'", nullable = false)
    private String productSn;

    @Column(name = "delete_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '删除状态：0->未删除；1->已删除'", nullable = false)
    private Integer deleteStatus;

    @Column(name = "publish_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '上架状态：0->下架；1->上架'", nullable = false)
    private Integer publishStatus;

    @Column(name = "new_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '新品状态:0->不是新品；1->新品'", nullable = false)
    private Integer newStatus;

    @Column(name = "recommand_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '推荐状态；0->不推荐；1->推荐'", nullable = false)
    private Integer recommandStatus;

    @Column(name = "verify_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '审核状态：0->未审核；1->审核通过'", nullable = false)
    private Integer verifyStatus;

    @Column(name = "sort", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '排序'", nullable = false)
    private Integer sort;

    @Column(name = "sale", columnDefinition = "int unsigned DEFAULT '0' COMMENT '销量'", nullable = false)
    private Integer sale;

    @Column(name = "price", columnDefinition = "decimal(10,2) default '0' COMMENT '售价'", nullable = false)
    private BigDecimal price;

    @Column(name = "promotion_price", columnDefinition = "decimal(10,2) default '0' COMMENT '促销价格'", nullable = false)
    private BigDecimal promotionPrice;

    @Column(name = "gift_growth", columnDefinition = "int unsigned COMMENT '赠送的成长值'", nullable = false)
    private Integer giftGrowth;

    @Column(name = "gift_point", columnDefinition = "int unsigned COMMENT '赠送的积分'", nullable = false)
    private Integer giftPoint;

    @Column(name = "use_point_limit", columnDefinition = "int unsigned COMMENT '限制使用的积分数'", nullable = false)
    private Integer usePointLimit;

    @Column(name = "sub_title", columnDefinition = "varchar(64) COMMENT '副标题'", nullable = false)
    private String subTitle;

    @Column(name = "original_price", columnDefinition = "decimal(10,2) default '0' COMMENT '市场价'", nullable = false)
    private BigDecimal originalPrice;

    @Column(name = "stock", columnDefinition = "int unsigned COMMENT '库存'", nullable = false)
    private Integer stock;

    @Column(name = "low_stock", columnDefinition = "int unsigned COMMENT '库存预警值'", nullable = false)
    private Integer lowStock;

    @Column(name = "unit", columnDefinition = "varchar(64) COMMENT '单位'", nullable = false)
    private String unit;

    @Column(name = "weight", columnDefinition = "decimal(10,2) default '0' COMMENT '商品重量，默认为克'", nullable = false)
    private BigDecimal weight;

    @Column(name = "preview_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否为预告商品：0->不是；1->是'", nullable = false)
    private Integer previewStatus;

    @Column(name = "service_ids", columnDefinition = "varchar(64) COMMENT '以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮'", nullable = false)
    private String serviceIds;
    @Column(name = "keywords", columnDefinition = "varchar(64) COMMENT '关键词'", nullable = false)
    private String keywords;
    @Column(name = "note", columnDefinition = "varchar(64) COMMENT '备注'", nullable = false)
    private String note;

    @Column(name = "album_pics", columnDefinition = "varchar(64) COMMENT '画册图片，连产品图片限制为5张，以逗号分割'", nullable = false)
    private String albumPics;
    @Column(name = "detail_title", columnDefinition = "varchar(64) COMMENT '商品title'", nullable = false)
    private String detailTitle;
    @Column(name = "description", columnDefinition = "varchar(64) COMMENT '商品描述'", nullable = false)
    private String description;

    @Column(name = "detail_desc", columnDefinition = "text COMMENT '商品详情'", nullable = false)
    private String detailDesc;


    @Column(name = "detail_html", columnDefinition = "varchar(64) COMMENT '产品详情网页内容'", nullable = false)
    private String detailHtml;

    @Column(name = "detail_mobile_html", columnDefinition = "varchar(64) COMMENT '移动端网页详情'", nullable = false)
    private String detailMobileHtml;

    @Column(name = "promotion_start_time", columnDefinition = "datetime COMMENT '促销开始时间'")
    private Date promotionStartTime;

    @Column(name = "promotion_end_time", columnDefinition = "datetime COMMENT '促销结束时间'")
    private Date promotionEndTime;

    @Column(name = "promotion_per_limit", columnDefinition = "datetime COMMENT '活动限购数量'")
    private Integer promotionPerLimit;

    @Column(name = "promotion_type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购'", nullable = false)
    private Integer promotionType;

    @Column(name = "brand_name", columnDefinition = "varchar(64) COMMENT '品牌名称'", nullable = false)
    private String brandName;

    @Column(name = "product_category_name", columnDefinition = "varchar(64) COMMENT '商品分类名称'", nullable = false)
    private String productCategoryName;


}