package com.mouse.api.dao.entity.sms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ; lidongdong
 * @Description 首页轮播广告表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "sms_home_advertise")
public class SmsHomeAdvertiseEntity implements Serializable {
    private static final long serialVersionUID = -1167969414530674291L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '首页轮播广告名称'", nullable = false)
    private String name;

    @Column(name = "type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '轮播位置：0->PC首页轮播；1->app首页轮播'", nullable = false)
    private Integer type;

    @Column(name = "pic", columnDefinition = "varchar(500) COMMENT '展示图片'", nullable = false)
    private String pic;

    @Column(name = "start_time", columnDefinition = "datetime COMMENT '活动开始时间'")
    private Date startTime;

    @Column(name = "end_time", columnDefinition = "datetime COMMENT '活动结束时间'")
    private Date endTime;

    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '上下线状态：0->下线；1->上线'", nullable = false)
    private Integer status;

    @Column(name = "click_count", columnDefinition = "int unsigned DEFAULT '1' COMMENT '点击数'", nullable = false)
    private Integer clickCount;

    @Column(name = "order_count", columnDefinition = "int unsigned DEFAULT '1' COMMENT '下单数'", nullable = false)
    private Integer orderCount;

    @Column(name = "url", columnDefinition = "varchar(500) COMMENT '链接地址'", nullable = false)
    private String url;

    @Column(name = "note", columnDefinition = "varchar(64) COMMENT '备注'", nullable = false)
    private String note;

    @Column(name = "sort", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '排序'", nullable = false)
    private Integer sort;

}