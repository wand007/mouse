package com.mouse.api.dao.entity.sms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ; lidongdong
 * @Description 限时购场次表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "sms_flash_promotion_session")
public class SmsFlashPromotionSessionEntity implements Serializable {
    private static final long serialVersionUID = -8967890600286096550L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '场次名称'", nullable = false)
    private String name;

    @Column(name = "start_time", columnDefinition = "datetime COMMENT '每日开始时间'")
    private Date startTime;

    @Column(name = "end_time", columnDefinition = "datetime COMMENT '每日结束时间'")
    private Date endTime;

    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '启用状态：0->不启用；1->启用'", nullable = false)
    private Integer status;

}