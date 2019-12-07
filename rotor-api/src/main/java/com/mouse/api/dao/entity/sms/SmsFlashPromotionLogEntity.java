package com.mouse.api.dao.entity.sms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ; lidongdong
 * @Description 限时购通知记录
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "sms_flash_promotion_log")
public class SmsFlashPromotionLogEntity implements Serializable {
    private static final long serialVersionUID = -5830016384385295204L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", columnDefinition = "bigint(20) unsigned COMMENT '会员ID'", nullable = false)
    private Long memberId;

    @Column(name = "product_id", columnDefinition = "bigint(20) unsigned COMMENT '商品记录ID'", nullable = false)
    private Long productId;

    @Column(name = "member_phone", columnDefinition = "varchar(64) COMMENT '领取人昵称'", nullable = false)
    private String memberPhone;

    @Column(name = "product_name", columnDefinition = "varchar(500) COMMENT '商品名称'", nullable = false)
    private String productName;

    @Column(name = "subscribe_time", columnDefinition = "datetime COMMENT '会员订阅时间'")
    private Date subscribeTime;

    @Column(name = "send_time", columnDefinition = "datetime COMMENT '结束日期'")
    private Date endTime;

}