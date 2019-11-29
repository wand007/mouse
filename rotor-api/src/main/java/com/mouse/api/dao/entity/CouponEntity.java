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
 * @Description 优惠券
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_coupon")
public class CouponEntity implements Serializable {

    private static final long serialVersionUID = 5790502074239554461L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", columnDefinition = "varchar(60) COMMENT '优惠券名称'", nullable = false)
    private String name;
    @Column(name = "send_type", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '发放方式'", nullable = false)
    private Integer sendType;
    @Column(name = "type_money", columnDefinition = "decimal(10,2) default '0' COMMENT '优惠券金额'", nullable = false)
    private BigDecimal typeMoney;
    @Column(name = "min_amount", columnDefinition = "decimal(10,2) default '0' COMMENT '最小金额'", nullable = false)
    private BigDecimal minAmount;
    @Column(name = "max_amount", columnDefinition = "decimal(10,2) default '0' COMMENT '最大金额'", nullable = false)
    private BigDecimal maxAmount;
    @Column(name = "min_goods_amount", columnDefinition = "decimal(10,2) default '0' COMMENT '最小使用金额'", nullable = false)
    private BigDecimal min_goods_amount;
    @Column(name = "min_goods_amount", columnDefinition = "decimal(10,2) default '0' COMMENT '最小商品金额'", nullable = false)
    private BigDecimal minGoodsAmount;
    @Column(name = "send_start_date", columnDefinition = "datetime COMMENT '发放时间'")
    private Date sendStartDate;
    @Column(name = "send_end_date", columnDefinition = "datetime COMMENT '发放时间'")
    private Date sendEndDate;
    @Column(name = "use_start_date", columnDefinition = "datetime COMMENT '使用开始时间'")
    private Date useStartDate;
    @Column(name = "use_end_date", columnDefinition = "datetime COMMENT '使用结束时间'")
    private Date useEndDate;
    @Column(name = "merchant_id", columnDefinition = "int default '0' COMMENT '商户id'", nullable = false)
    private Integer merchantId;
    @Column(name = "total_count", columnDefinition = "int default '0' COMMENT '发放总数量'", nullable = false)
    private Integer totalCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
