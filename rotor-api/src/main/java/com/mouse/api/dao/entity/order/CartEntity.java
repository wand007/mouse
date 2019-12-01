package com.mouse.api.dao.entity.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author ; lidongdong
 * @Description 购物车
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_cart")
public class CartEntity implements Serializable {

    private static final long serialVersionUID = -3550564466917149291L;
    @Id
    @Column(name = "id", columnDefinition = "int unsigned default '0' COMMENT '购物车Id'", nullable = false)
    private String id;
    @Column(name = "user_id", columnDefinition = "varchar(32) COMMENT '会员Id'", nullable = false)
    private String userId;
    @Column(name = "merchant_id", columnDefinition = "varchar(32) COMMENT '商户id'", nullable = false)
    private String merchantId;
    @Column(name = "session_id", columnDefinition = "varchar(60) COMMENT 'sessionId'", nullable = false)
    private String sessionId;
    @Column(name = "goods_id", columnDefinition = "int unsigned default '0' COMMENT '商品Id'", nullable = false)
    private Integer goodsId;
    @Column(name = "goods_sn", columnDefinition = "varchar(60) COMMENT '商品序列号'", nullable = false)
    private String goodsSn;
    @Column(name = "product_id", columnDefinition = "int unsigned default '0' COMMENT '产品Id'", nullable = false)
    private Integer productId;
    @Column(name = "goods_name", columnDefinition = "varchar(60) COMMENT '产品名称'", nullable = false)
    private String goodsName;
    @Column(name = "market_price", columnDefinition = "decimal(10,2) default '0' COMMENT '市场价'", nullable = false)
    private BigDecimal marketPrice;
    @Column(name = "retail_price", columnDefinition = "decimal(10,2) default '0' COMMENT '零售价格'", nullable = false)
    private BigDecimal retailPrice;
    @Column(name = "number", columnDefinition = "int unsigned default '0' COMMENT '数量'", nullable = false)
    private Integer number;
    @Column(name = "goods_specifition_name_value", columnDefinition = "varchar(60) COMMENT '规格属性组成的字符串，用来显示用'", nullable = false)
    private String goodsSpecifitionNameValue;
    @Column(name = "goods_specifition_ids", columnDefinition = "varchar(60) COMMENT 'product表对应的goods_specifition_ids'", nullable = false)
    private String goodsSpecifitionIds;
    @Column(name = "checked", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否勾选(如果勾选，子节点也会全部勾选)'", nullable = false)
    private Boolean checked;
    @Column(name = "list_pic_url", columnDefinition = "varchar(60) COMMENT '商品列表图'", nullable = false)
    private String listPicUrl;
    @Column(name = "customers_id", columnDefinition = "varchar(60) COMMENT '客户ID'", nullable = false)
    private String customersId;
    @Column(name = "customers_name", columnDefinition = "varchar(60) COMMENT '客户名称'", nullable = false)
    private String customersName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
