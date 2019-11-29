package com.mouse.api.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    @Column(name = "category_id", columnDefinition = "int default '0' COMMENT '商品类型Id'", nullable = false)
    private Integer categoryId;
    @Column(name = "goods_sn", columnDefinition = "varchar(60) COMMENT '商品序列号'", nullable = false)
    private String goodsSn;
    @Column(name = "name", columnDefinition = "varchar(60) COMMENT '名称'", nullable = false)
    private String name;
    @Column(name = "brand_id", columnDefinition = "int default '0' COMMENT '品牌Id'", nullable = false)
    private Integer brandId;
    @Column(name = "goods_number", columnDefinition = "int default '0' COMMENT '商品库存'", nullable = false)
    private Integer goodsNumber;
    @Column(name = "keywords", columnDefinition = "varchar(60) COMMENT '关键字'", nullable = false)
    private String keywords;
    @Column(name = "goods_brief", columnDefinition = "varchar(60) COMMENT '简明介绍'", nullable = false)
    private String goodsBrief;
    @Column(name = "goods_desc", columnDefinition = "varchar(255) COMMENT '商品描述'", nullable = false)
    private String goodsDesc;
    @Column(name = "is_on_sale", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '上架'", nullable = false)
    private Boolean isOnSale;
    @Column(name = "sort_order", columnDefinition = "tinyint unsigned DEFAULT '50' COMMENT '排序'", nullable = false)
    private Integer sortOrder;
    @Column(name = "is_delete", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '删除状态'", nullable = false)
    private Boolean isDelete;
    @Column(name = "attribute_category", columnDefinition = "int default '0' COMMENT '属性类别'", nullable = false)
    private Integer attributeCategory;
    //专柜价格
    private BigDecimal counterPrice;
    //附加价格
    private BigDecimal extraPrice;
    //是否新商品
    private Integer isNew;
    //商品单位
    private String goodsUnit;
    //商品主图
    private String primaryPicUrl;
    //商品列表图
    private String listPicUrl;
    //零售价格
    private BigDecimal retailPrice;
    //销售量
    private Integer sellVolume;
    //主sku　product_id
    private Integer primaryProductId;
    //单位价格，单价
    private BigDecimal unitPrice;
    //推广描述
    private String promotionDesc;
    //推广标签
    private String promotionTag;
    //APP专享价
    private BigDecimal appExclusivePrice;
    //是否是APP专属
    private Integer isAppExclusive;
    //限购
    private Integer isLimited;
    //热销
    private Integer isHot;
    //市场价
    private BigDecimal marketPrice;
	private Integer isSecKill;//商品类型,1:普通商品,2:秒杀,3:团购,4:砍价
	//开始时间
	private Date startTime;
    /**
     * 用户ID
     */
    private Long createUserId;
    /**
     * 用户ID
     */
    private Long createUserDeptId;
    //分佣百分比
    private String brokerage_percent;
    /**
     * 用户ID
     */
    private Long updateUserId;

    List<GoodsAttributeEntity> attributeEntityList = new ArrayList<>();

    List<GoodsGalleryEntity> goodsImgList = new ArrayList<>();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
