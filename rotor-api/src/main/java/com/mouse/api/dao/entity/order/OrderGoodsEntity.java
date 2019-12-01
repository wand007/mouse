package com.mouse.api.dao.entity.order;

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
@Table(name = "tbl_order_goods")
public class OrderGoodsEntity implements Serializable {

	private static final long serialVersionUID = -2984301002748203289L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "order_id", columnDefinition = "varchar(32) COMMENT '订单ID'", nullable = false)
	private String orderId;
	@Column(name = "goods_id", columnDefinition = "int unsigned default '0' COMMENT '商品Id'", nullable = false)
	private Integer goodsId;
	@Column(name = "goods_name", columnDefinition = "varchar(60) COMMENT '产品名称'", nullable = false)
	private String goodsName;
	@Column(name = "goods_sn", columnDefinition = "varchar(60) COMMENT '商品序列号'", nullable = false)
	private String goodsSn;
	@Column(name = "product_id", columnDefinition = "int unsigned default '0' COMMENT '产品Id'", nullable = false)
	private Integer productId;
	@Column(name = "brand_id", columnDefinition = "int unsigned default '0' COMMENT '供应商ID'", nullable = false)
	private Integer brandId;
	@Column(name = "coupon_id", columnDefinition = "int unsigned default '0' COMMENT '优惠券ID'", nullable = false)
	private Integer couponId;
	@Column(name = "number", columnDefinition = "int unsigned default '0' COMMENT '数量'", nullable = false)
	private Integer number;
	@Column(name = "market_price", columnDefinition = "decimal(10,2) default '0' COMMENT '市场价'", nullable = false)
	private BigDecimal marketPrice;
	@Column(name = "retail_price", columnDefinition = "decimal(10,2) default '0' COMMENT '零售价格'", nullable = false)
	private BigDecimal retailPrice;
	@Column(name = "goods_specifition_name_value", columnDefinition = "text COMMENT '商品规格详情'", nullable = false)
	private String goodsSpecifitionNameValue;
	@Column(name = "is_real", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '虚拟商品'", nullable = false)
	private Boolean isReal;
	@Column(name = "goods_specifition_ids", columnDefinition = "varchar(255) COMMENT '商品规格Ids'", nullable = false)
	private String goodsSpecifitionIds;
	@Column(name = "list_pic_url", columnDefinition = "varchar(255) COMMENT '图片链接'", nullable = false)
	private String listPicUrl;
	@Column(name = "customers", columnDefinition = "varchar(255) COMMENT '客户'", nullable = false)
    private String customers;
	@Column(name = "customers_name", columnDefinition = "varchar(255) COMMENT '客户名称'", nullable = false)
    private String customersName;
	@Column(name = "consignee", columnDefinition = "varchar(32) COMMENT '收货人'", nullable = false)
	private String consignee;
	@Column(name = "country", columnDefinition = "varchar(32) COMMENT '国家'", nullable = false)
	private String country;
	@Column(name = "province", columnDefinition = "varchar(32) COMMENT '省'", nullable = false)
	private String province;
	@Column(name = "city", columnDefinition = "varchar(32) COMMENT '地市'", nullable = false)
	private String city;
	@Column(name = "district", columnDefinition = "varchar(32) COMMENT '区县'", nullable = false)
	private String district;
	@Column(name = "address", columnDefinition = "varchar(32) COMMENT '收货地址'", nullable = false)
	private String address;
	@Column(name = "mobile", columnDefinition = "char(15) COMMENT '联系电话'", nullable = false)
	private String mobile;
	@Column(name = "shipping_id", columnDefinition = "int unsigned default '0' COMMENT '快递公司Id'", nullable = false)
	private Integer shippingId;
	@Column(name = "shipping_name", columnDefinition = "varchar(32) COMMENT '快递公司名称'", nullable = false)
	private String shippingName;
	@Column(name = "shipping_no", columnDefinition = "varchar(32) COMMENT '快递单号'", nullable = false)
	private String shippingNo;
	@Column(name = "shipping_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '发货状态 商品配送情况;0未发货,1已发货,2已收货,4退货'", nullable = false)
	private Integer shippingStatus;
	@Column(name = "order_price", columnDefinition = "decimal(10,2) default '0' COMMENT '订单总价'", nullable = false)
	private BigDecimal orderPrice;
	@Column(name = "goods_price", columnDefinition = "decimal(10,2) default '0' COMMENT '商品总价'", nullable = false)
	private BigDecimal goodsPrice;
	@Column(name = "actual_price", columnDefinition = "decimal(10,2) default '0' COMMENT '实际需要支付的金额'", nullable = false)
	private BigDecimal actualPrice;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@CreationTimestamp
	@Column(name = "create_date")
	public Date createDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@UpdateTimestamp
	@Column(name = "update_date")
	public Date updateDate;
}
