package com.mouse.api.dao.entity.pms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 商品审核记录
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "pms_product_vertify_record")
public class PmsProductVertifyRecordEntity implements Serializable {
    private static final long serialVersionUID = 8044463806087365884L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", columnDefinition = "bigint(20) unsigned COMMENT '商品记录ID'", nullable = false)
    private Long productId;

    @Column(name = "operate_man", columnDefinition = "varchar(64) COMMENT '审核人'", nullable = false)
    private String operateMan;

    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '审核状态：0->不通过；1->通过'", nullable = false)
    private Integer status;

    @Column(name = "detail", columnDefinition = "varchar(64) COMMENT '反馈详情'", nullable = false)
    private String detail;

}