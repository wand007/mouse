package com.mouse.api.dao.entity.sms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
/**
 * @author ; lidongdong
 * @Description 人气推荐商品表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "sms_home_recommend_product")
public class SmsHomeRecommendProductEntity implements Serializable {

    private static final long serialVersionUID = -8925969087685611000L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", columnDefinition = "bigint(20) unsigned COMMENT '商品记录ID'", nullable = false)
    private Long productId;

    @Column(name = "product_name", columnDefinition = "varchar(500) COMMENT '商品名称'", nullable = false)
    private String productName;

    @Column(name = "recommend_status", columnDefinition = "int unsigned DEFAULT '0' COMMENT '推荐数量'", nullable = false)
    private Integer recommendStatus;

    @Column(name = "sort", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '排序'", nullable = false)
    private Integer sort;
}