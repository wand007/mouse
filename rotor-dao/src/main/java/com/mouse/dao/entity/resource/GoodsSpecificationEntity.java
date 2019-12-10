package com.mouse.dao.entity.resource;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 商品规格表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_goods_specification")
public class GoodsSpecificationEntity implements Serializable {
    private static final long serialVersionUID = -1229960725735863374L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "goods_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '商品表的商品ID'", nullable = false)
    private Integer goodsId;

    //    @ElementCollection
    @Column(name = "specifications", columnDefinition = "varchar(1023) DEFAULT '[]' COMMENT '商品规格值列表，采用JSON数组格式'", nullable = false)
    private String[] specifications;


    @Column(name = "value", columnDefinition = "varchar(500) COMMENT '商品规格值'", nullable = false)
    private String value;

    @Column(name = "pic_url", columnDefinition = "varchar(500) COMMENT '商品规格图片'", nullable = false)
    private String picUrl;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

    @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除'", nullable = false)
    private Boolean deleted;
}