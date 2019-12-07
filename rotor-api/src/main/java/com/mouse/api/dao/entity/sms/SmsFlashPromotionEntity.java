package com.mouse.api.dao.entity.sms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ; lidongdong
 * @Description 限时购表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "sms_flash_promotion")
public class SmsFlashPromotionEntity implements Serializable {
    private static final long serialVersionUID = -7590543207703574357L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", columnDefinition = "varchar(60) COMMENT '秒杀时间段名称'", nullable = false)
    private String title;

    @Column(name = "start_date", columnDefinition = "datetime COMMENT '开始日期'")
    private Date startDate;

    @Column(name = "end_date", columnDefinition = "datetime COMMENT '结束日期'")
    private Date endDate;

    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '上下线状态：0->下线；1->上线；'", nullable = false)
    private Boolean status;

    @Column(name = "create_time", columnDefinition = "datetime COMMENT '创建时间'")
    private Date createTime;

}