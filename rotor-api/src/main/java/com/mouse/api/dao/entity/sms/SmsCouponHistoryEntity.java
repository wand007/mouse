package com.mouse.api.dao.entity.sms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ; lidongdong
 * @Description 优惠券使用、领取历史表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "sms_coupon_history")
public class SmsCouponHistoryEntity implements Serializable {
    private static final long serialVersionUID = -3787248819462003890L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coupon_id", columnDefinition = "bigint(20) unsigned COMMENT '优惠券ID'", nullable = false)
    private Long couponId;

    @Column(name = "order_id", columnDefinition = "bigint(20) unsigned COMMENT '订单id'", nullable = false)
    private Long orderId;

    @Column(name = "order_sn", columnDefinition = "varchar(32) COMMENT '订单序列号'", nullable = false)
    private String orderSn;

    @Column(name = "coupon_code", columnDefinition = "varchar(64) COMMENT '优惠码'", nullable = false)
    private String couponCode;

    @Column(name = "member_nick_name", columnDefinition = "varchar(64) COMMENT '领取人昵称'", nullable = false)
    private String memberNickName;

    @Column(name = "member_id", columnDefinition = "bigint(20) unsigned COMMENT '会员ID'", nullable = false)
    private Long memberId;

    @Column(name = "gain_mode", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '获取类型：0->后台赠送；1->主动获取'", nullable = false)
    private Integer gainMode;

    @Column(name = "use_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '使用状态：0->未使用；1->已使用；2->已过期'", nullable = false)
    private Integer useStatus;

    @Column(name = "use_time", columnDefinition = "datetime COMMENT '使用时间'")
    private Date useTime;


}