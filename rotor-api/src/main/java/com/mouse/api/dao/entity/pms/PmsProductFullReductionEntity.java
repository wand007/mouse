package com.mouse.api.dao.entity.pms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ; lidongdong
 * @Description 产品满减表(只针对同商品)
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "pms_product_full_reduction")
public class PmsProductFullReductionEntity implements Serializable {
    private static final long serialVersionUID = 1970705421097909270L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", columnDefinition = "bigint(20) unsigned COMMENT '商品记录ID'", nullable = false)
    private Long productId;

    @Column(name = "full_price", columnDefinition = "decimal(10,2) default '0' COMMENT '全价'", nullable = false)
    private BigDecimal fullPrice;

    @Column(name = "reduce_price", columnDefinition = "decimal(10,2) default '0' COMMENT '减价'", nullable = false)
    private BigDecimal reducePrice;

}