package com.mouse.dao.entity.resource;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 品牌商表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_brand")
public class BrandEntity implements Serializable {
    private static final long serialVersionUID = -8090696158333443477L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "\"name\"", columnDefinition = "varchar(255) COMMENT '品牌商名称'", nullable = false)
    private String name;

    @Column(name = "\"desc\"", columnDefinition = "varchar(255) COMMENT '品牌商简介'", nullable = false)
    private String desc;

    @Column(name = "pic_url", columnDefinition = "varchar(255) COMMENT '品牌商页的品牌商图片'", nullable = false)
    private String picUrl;

    @Column(name = "sort_order", columnDefinition = "tinyint unsigned DEFAULT '50' COMMENT '排序'", nullable = false)
    private Integer sortOrder;

    @Column(name = "floor_price", columnDefinition = "decimal(10,2) default '0' COMMENT '品牌商的商品低价，仅用于页面展示'", nullable = false)
    private BigDecimal floorPrice;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

    @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除'", nullable = false)
    private Boolean deleted;

}