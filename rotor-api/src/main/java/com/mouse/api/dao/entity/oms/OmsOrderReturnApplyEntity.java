package com.mouse.api.dao.entity.oms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ; lidongdong
 * @Description 订单退货申请
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "oms_order_return_apply")
public class OmsOrderReturnApplyEntity implements Serializable {
    private static final long serialVersionUID = 75098517294567916L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", columnDefinition = "bigint(20) unsigned COMMENT '订单id'", nullable = false)
    private Long orderId;

    @Column(name = "company_address_id", columnDefinition = "bigint(20) unsigned COMMENT '收货地址表id'", nullable = false)
    private Long companyAddressId;

    @Column(name = "product_id", columnDefinition = "bigint(20) unsigned COMMENT '退货商品id'", nullable = false)
    private Long productId;

    @Column(name = "order_sn", columnDefinition = "varchar(200) COMMENT '订单编号'", nullable = false)
    private String orderSn;

    @Column(name = "member_username", columnDefinition = "varchar(64) COMMENT '会员用户名'", nullable = false)
    private String memberUsername;

    @Column(name = "return_amount", columnDefinition = "decimal(10,2) default '0' COMMENT '退款金额'", nullable = false)
    private BigDecimal returnAmount;

    @Column(name = "return_name", columnDefinition = "varchar(64) COMMENT '退货人姓名'", nullable = false)
    private String returnName;

    @Column(name = "return_phone", columnDefinition = "varchar(64) COMMENT '退货人电话'", nullable = false)
    private String returnPhone;

    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝'", nullable = false)
    private Integer status;

    @Column(name = "product_pic", columnDefinition = "varchar(64) COMMENT '商品图片'", nullable = false)
    private String productPic;

    @Column(name = "product_name", columnDefinition = "varchar(64) COMMENT '商品名称'", nullable = false)
    private String productName;

    @Column(name = "product_brand", columnDefinition = "varchar(64) COMMENT '商品品牌'", nullable = false)
    private String productBrand;

    @Column(name = "product_attr", columnDefinition = "varchar(500) COMMENT '商品销售属性:[{'key':'颜色','value':'颜色'},{'key':'容量','value':'4G'}]'", nullable = false)
    private String productAttr;

    @Column(name = "product_count", columnDefinition = "int unsigned DEFAULT '0' COMMENT '退货数量'", nullable = false)
    private Integer productCount;

    @Column(name = "product_price", columnDefinition = "decimal(10,2) default '0' COMMENT '商品单价'", nullable = false)
    private BigDecimal productPrice;

    @Column(name = "product_real_price", columnDefinition = "decimal(10,2) default '0' COMMENT '商品实际支付单价'", nullable = false)
    private BigDecimal productRealPrice;

    @Column(name = "reason", columnDefinition = "varchar(64) COMMENT '原因'", nullable = false)
    private String reason;

    @Column(name = "description", columnDefinition = "varchar(64) COMMENT '描述'", nullable = false)
    private String description;

    @Column(name = "proof_pics", columnDefinition = "varchar(64) COMMENT '凭证图片，以逗号隔开'", nullable = false)
    private String proofPics;

    @Column(name = "handle_note", columnDefinition = "varchar(64) COMMENT '处理备注'", nullable = false)
    private String handleNote;

    @Column(name = "handle_man", columnDefinition = "varchar(64) COMMENT '处理人员'", nullable = false)
    private String handleMan;

    @Column(name = "receive_man", columnDefinition = "varchar(64) COMMENT '收货人'", nullable = false)
    private String receiveMan;

    @Column(name = "receive_note", columnDefinition = "varchar(64) COMMENT '收货备注'", nullable = false)
    private String receiveNote;

    @Column(name = "receive_time", columnDefinition = "datetime COMMENT '收货时间'")
    private Date receiveTime;

    @Column(name = "handle_time", columnDefinition = "datetime COMMENT '处理时间'")
    private Date handleTime;


}