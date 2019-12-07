package com.mouse.api.dao.entity.ums;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 积分消费设置
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "ums_integration_consume_setting")
public class UmsIntegrationConsumeSettingEntity implements Serializable {
    private static final long serialVersionUID = 7127632072255223510L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deduction_per_amount", columnDefinition = "int unsigned DEFAULT '0' COMMENT '每一元需要抵扣的积分数量'", nullable = false)
    private Integer deductionPerAmount;

    @Column(name = "max_percent_per_order", columnDefinition = "int unsigned DEFAULT '0' COMMENT '每笔订单最高抵用百分比'", nullable = false)
    private Integer maxPercentPerOrder;

    @Column(name = "use_unit", columnDefinition = "int unsigned DEFAULT '0' COMMENT '每次使用积分最小单位100'", nullable = false)
    private Integer useUnit;

    @Column(name = "coupon_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否可以和优惠券同用；0->不可以；1->可以'", nullable = false)
    private Integer couponStatus;

}