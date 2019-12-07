package com.mouse.api.dao.entity.pms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
/**
 * @author ; lidongdong
 * @Description 运费模版
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "pms_feight_template")
public class PmsFeightTemplateEntity implements Serializable {
    private static final long serialVersionUID = -122381710488361990L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '模版名称'", nullable = false)
    private String name;

    @Column(name = "charge_type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '计费类型:0->按重量；1->按件数'", nullable = false)
    private Integer chargeType;

    @Column(name = "first_weight", columnDefinition = "decimal(10,2) default '0' COMMENT '首重kg'", nullable = false)
    private BigDecimal firstWeight;

    @Column(name = "first_fee", columnDefinition = "decimal(10,2) default '0' COMMENT '首费（元）'", nullable = false)
    private BigDecimal firstFee;

    @Column(name = "continue_weight", columnDefinition = "decimal(10,2) default '0' COMMENT '持续重量kg'", nullable = false)
    private BigDecimal continueWeight;

    @Column(name = "continme_fee", columnDefinition = "decimal(10,2) default '0' COMMENT '递增收费'", nullable = false)
    private BigDecimal continmeFee;

    @Column(name = "dest", columnDefinition = "varchar(64) COMMENT '目的地（省、市）'", nullable = false)
    private String dest;

}