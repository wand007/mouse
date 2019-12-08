package com.mouse.api.dao.entity.sms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ; lidongdong
 * @Description 商品限时购与商品关系表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "sms_flash_promotion_product_relation")
public class SmsFlashPromotionProductRelationEntity implements Serializable {
    private static final long serialVersionUID = 1191137288006234613L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", columnDefinition = "bigint(20) unsigned COMMENT '商品记录ID'", nullable = false)
    private Long productId;

    @Column(name = "flash_promotion_id", columnDefinition = "bigint(20) unsigned COMMENT '会员ID'", nullable = false)
    private Long flashPromotionId;

    @Column(name = "flash_promotion_session_id", columnDefinition = "bigint(20) unsigned COMMENT '编号'", nullable = false)
    private Long flashPromotionSessionId;

    @Column(name = "flash_promotion_price", columnDefinition = "decimal(10,2) default '0' COMMENT '限时购价格'", nullable = false)
    private BigDecimal flashPromotionPrice;

    @Column(name = "flash_promotion_count", columnDefinition = "int  DEFAULT '1' COMMENT '限时购总数量'", nullable = false)
    private Integer flashPromotionCount;

    @Column(name = "flash_promotion_limit", columnDefinition = "int  DEFAULT '1' COMMENT '每人限购数量'", nullable = false)
    private Integer flashPromotionLimit;

    @Column(name = "sort", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '排序'", nullable = false)
    private Integer sort;
}