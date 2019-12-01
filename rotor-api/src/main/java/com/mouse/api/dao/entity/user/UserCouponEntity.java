package com.mouse.api.dao.entity.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @author ; lidongdong
 * @Description 用户优惠券
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_user_coupon")
public class UserCouponEntity implements Serializable {

    private static final long serialVersionUID = -1753758835944880241L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "coupon_id", columnDefinition = "int unsigned default '0' COMMENT '使用的优惠券id'", nullable = false)
    private Integer couponId;
    @Column(name = "coupon_number", columnDefinition = "int unsigned default '0' COMMENT '优惠券数量'", nullable = false)
    private String couponNumber;
    @Column(name = "user_id", columnDefinition = "varchar(32) COMMENT '会员ID'", nullable = false)
    private String userId;
    @Column(name = "order_id", columnDefinition = "varchar(32) COMMENT '订单ID'", nullable = false)
    private String orderId;
    @Column(name = "used_time", columnDefinition = "datetime COMMENT '使用时间'")
    private Date usedTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
