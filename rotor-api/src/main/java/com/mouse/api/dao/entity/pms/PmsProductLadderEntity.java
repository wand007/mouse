package com.mouse.api.dao.entity.pms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ; lidongdong
 * @Description 产品阶梯价格表(只针对同商品)
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "pms_product_ladder")
public class PmsProductLadderEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", columnDefinition = "bigint(20) unsigned COMMENT '商品记录ID'", nullable = false)
    private Long productId;

    @Column(name = "count", columnDefinition = "int unsigned COMMENT '满足的商品数量'", nullable = false)
    private Integer count;

    @Column(name = "discount", columnDefinition = "decimal(10,2) default '0' COMMENT '折扣'", nullable = false)
    private BigDecimal discount;


    @Column(name = "price", columnDefinition = "decimal(10,2) default '0' COMMENT '折后价格'", nullable = false)
    private BigDecimal price;

}