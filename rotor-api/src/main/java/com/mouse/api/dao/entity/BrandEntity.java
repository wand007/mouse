package com.mouse.api.dao.entity;

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
@Table(name = "tbl_brand")
public class BrandEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", columnDefinition = "varchar(60) COMMENT '品牌名称'", nullable = false)
    private String name;
    @Column(name = "user_id", columnDefinition = "int COMMENT '管理员Id'", nullable = false)
    private Integer userId;
    @Column(name = "merchant_id", columnDefinition = "int default '0' COMMENT '商户id'", nullable = false)
    private Integer merchantId;
    @Column(name = "brand_type", columnDefinition = "tinyint unsigned DEFAULT '8' COMMENT '商户类型,1:置顶,2:推荐,8:普通'", nullable = false)
    private Integer brandType;
    @Column(name = "is_new", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '新品牌 1是新品牌; 0不是新品牌'", nullable = false)
    private Boolean isNew;
    @Column(name = "is_show", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '显示 1显示; 0不显示'", nullable = false)
    private Boolean isShow;
    @Column(name = "sort_order", columnDefinition = "tinyint unsigned DEFAULT '50' COMMENT '排序'", nullable = false)
    private Integer sortOrder;
    @Column(name = "sort_order", columnDefinition = "tinyint unsigned DEFAULT '50' COMMENT '排序'", nullable = false)
    private Integer newSortOrder;
    @Column(name = "floor_price", columnDefinition = "decimal(10,2) default '0' COMMENT '最低价'", nullable = false)
    private BigDecimal floorPrice;
    @Column(name = "max_discount", columnDefinition = "int default '100' COMMENT '最大折扣率'", nullable = false)
    private Integer maxDiscount;
    @Column(name = "sum_discount", columnDefinition = "decimal(10,2) default '0' COMMENT '总折扣金额'", nullable = false)
    private BigDecimal sumDiscount;
    @Column(name = "addr", columnDefinition = "varchar(60) COMMENT '地址'", nullable = false)
    private String addr;
    @Column(name = "logo", columnDefinition = "varchar(60) COMMENT 'logo'", nullable = false)
    private String logo;
    @Column(name = "pic_Url", columnDefinition = "varchar(60) COMMENT '轮播图片'", nullable = false)
    private String picUrl;
    @Column(name = "new_pic_url", columnDefinition = "varchar(60) COMMENT '新品牌轮播图片'", nullable = false)
    private String newPicUrl;
    @Column(name = "list_pic_url", columnDefinition = "varchar(60) COMMENT '商品列表图'", nullable = false)
    private String listPicUrl;
    @Column(name = "app_list_pic_url", columnDefinition = "varchar(60) COMMENT 'app显示图片URL'", nullable = false)
    private String appListPicUrl;
    @Column(name = "simple_desc", columnDefinition = "varchar(60) COMMENT '描述'", nullable = false)
    private String simpleDesc;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;

}
