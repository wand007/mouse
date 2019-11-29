package com.mouse.api.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @author ; lidongdong
 * @Description 优惠券关联商品实体
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_coupon_goods")
public class CouponGoodsEntity implements Serializable {

    private static final long serialVersionUID = 6752553365090280829L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "coupon_id", columnDefinition = "int default '0' COMMENT '优惠券Id'", nullable = false)
    private Integer couponId;
    @Column(name = "goods_id", columnDefinition = "int default '0' COMMENT '商品id'", nullable = false)
    private Integer goodsId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
