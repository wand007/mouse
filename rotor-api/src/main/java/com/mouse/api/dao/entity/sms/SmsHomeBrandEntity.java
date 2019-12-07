package com.mouse.api.dao.entity.sms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 首页推荐品牌表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "sms_home_brand")
public class SmsHomeBrandEntity implements Serializable {
    private static final long serialVersionUID = -607204093430324496L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "brand_id", columnDefinition = "bigint(20) unsigned COMMENT '品牌记录ID'", nullable = false)
    private Long brandId;

    @Column(name = "brand_name", columnDefinition = "varchar(64) COMMENT '品牌名称'", nullable = false)
    private String brandName;

    @Column(name = "recommend_status", columnDefinition = "int unsigned DEFAULT '0' COMMENT '推荐数量'", nullable = false)
    private Integer recommendStatus;

    @Column(name = "sort", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '排序'", nullable = false)
    private Integer sort;
}