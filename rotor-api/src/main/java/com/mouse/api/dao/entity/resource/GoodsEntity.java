package com.mouse.api.dao.entity.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_goods")
public class GoodsEntity implements Serializable {

    private static final long serialVersionUID = 8422071533446805581L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "category_id", columnDefinition = "int unsigned default '0' COMMENT '商品类型Id'", nullable = false)
    private Integer categoryId;
    @Column(name = "goods_sn", columnDefinition = "varchar(60) COMMENT '商品序列号'", nullable = false)
    private String goodsSn;
    @Column(name = "name", columnDefinition = "varchar(60) COMMENT '名称'", nullable = false)
    private String name;
    @Column(name = "brand_id", columnDefinition = "int unsigned default '0' COMMENT '品牌Id'", nullable = false)
    private Integer brandId;
    @Column(name = "goods_number", columnDefinition = "int unsigned default '0' COMMENT '商品库存'", nullable = false)
    private Integer goodsNumber;
    @Column(name = "primary_product_id", columnDefinition = "int unsigned default '0' COMMENT '主sku　product_id'", nullable = false)
    private Integer primaryProductId;

    @Column(name = "attribute_category", columnDefinition = "int unsigned default '0' COMMENT '属性类别'", nullable = false)
    private Integer attributeCategory;
    @Column(name = "sell_volume", columnDefinition = "int unsigned default '0' COMMENT '销售量'", nullable = false)
    private Integer sellVolume;

    @Column(name = "retail_price", columnDefinition = "decimal(10,2) default '0' COMMENT '零售价格'", nullable = false)
    private BigDecimal retailPrice;
    @Column(name = "counter_price", columnDefinition = "decimal(10,2) default '0' COMMENT '专柜价格'", nullable = false)
    private BigDecimal counterPrice;
    @Column(name = "market_price", columnDefinition = "decimal(10,2) default '0' COMMENT '市场价'", nullable = false)
    private BigDecimal marketPrice;
    @Column(name = "extra_price", columnDefinition = "decimal(10,2) default '0' COMMENT '附加价格'", nullable = false)
    private BigDecimal extraPrice;

    @Column(name = "app_exclusive_price", columnDefinition = "decimal(10,2) default '0' COMMENT 'APP专享价'", nullable = false)
    private BigDecimal appExclusivePrice;
    @Column(name = "goods_unit", columnDefinition = "varchar(60) COMMENT '商品单位'", nullable = false)
    private String goodsUnit;

    @Column(name = "unit_price", columnDefinition = "decimal(10,2) default '0' COMMENT '最小金额'", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "brokerage_percent", columnDefinition = "decimal(10,2) default '0' COMMENT '分佣百分比'", nullable = false)
    private BigDecimal brokeragePercent;


    @Column(name = "sort_order", columnDefinition = "tinyint unsigned DEFAULT '50' COMMENT '排序'", nullable = false)
    private Integer sortOrder;

    @Column(name = "primary_pic_url", columnDefinition = "varchar(60) COMMENT '商品主图'", nullable = false)
    private String primaryPicUrl;
    @Column(name = "list_pic_url", columnDefinition = "varchar(255) COMMENT '商品列表图'", nullable = false)
    private String listPicUrl;

    @Column(name = "goods_brief", columnDefinition = "varchar(60) COMMENT '简明介绍'", nullable = false)
    private String goodsBrief;
    @Column(name = "goods_desc", columnDefinition = "varchar(255) COMMENT '商品描述'", nullable = false)
    private String goodsDesc;
    @Column(name = "promotion_desc", columnDefinition = "varchar(255) COMMENT '推广描述'", nullable = false)
    private String promotionDesc;
    @Column(name = "promotion_tag", columnDefinition = "varchar(255) COMMENT '推广标签'", nullable = false)
    private String promotionTag;

    @Column(name = "keywords", columnDefinition = "varchar(255) COMMENT '关键字'", nullable = false)
    private String keywords;


    @Column(name = "is_new", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否新商品'", nullable = false)
    private Boolean isNew;
    @Column(name = "is_app_exclusive", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否是APP专属'", nullable = false)
    private Boolean isAppExclusive;
    @Column(name = "is_limited", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '限购'", nullable = false)
    private Boolean isLimited;
    @Column(name = "is_hot", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '热销'", nullable = false)
    private Boolean isHot;
    @Column(name = "is_service", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否是服务型商品'", nullable = false)
    private Boolean isService;

    /**
     * 商品类型,1:普通商品,2:秒杀,3:团购,4:砍价
     */
    @Column(name = "is_sec_kill", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否秒杀'", nullable = false)
    private Boolean isSecKill;
    @Column(name = "is_on_sale", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '上架'", nullable = false)
    private Boolean isOnSale;
    @Column(name = "is_delete", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '删除状态  1：已删除  0：正常'", nullable = false)
    private Boolean isDelete;

    @Column(name = "create_user_dept_id", columnDefinition = "int unsigned default '0' COMMENT '团长用户ID'", nullable = false)
    private Integer createUserDeptId;

    @Column(name = "start_time", columnDefinition = "datetime COMMENT '活动开始时间'")
    private Date startTime;

    @Column(name = "end_time", columnDefinition = "datetime COMMENT '活动结束时间'")
    private Date endTime;

    @Column(name = "success_time", columnDefinition = "int unsigned default '0' COMMENT '成团时间(分钟)'")
    private Integer successTime;


    @Column(name = "group_price", columnDefinition = "decimal(10,2) default '0' COMMENT '团购价格(元)'", nullable = false)
    private BigDecimal groupPrice;

    @Column(name = "success_people", columnDefinition = "int unsigned DEFAULT '0' COMMENT '成团人数'", nullable = false)
    private Integer successPeople;


    @Column(name = "create_user_id", columnDefinition = "varchar(32) COMMENT '创建用户ID'", nullable = false)
    private String createUserId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;

    @Column(name = "merchant_id", columnDefinition = "varchar(32) COMMENT '供应商ID'", nullable = false)
    private String merchantId;

    @Column(name = "update_user_id", columnDefinition = "varchar(32) COMMENT '更新用户ID'", nullable = false)
    private String updateUserId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;


}
