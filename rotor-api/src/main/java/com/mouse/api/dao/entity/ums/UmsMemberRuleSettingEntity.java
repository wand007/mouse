package com.mouse.api.dao.entity.ums;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ; lidongdong
 * @Description 会员积分成长规则表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "ums_member_rule_setting")
public class UmsMemberRuleSettingEntity implements Serializable {
    private static final long serialVersionUID = 4333751411776979784L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "continue_sign_day", columnDefinition = "int unsigned DEFAULT '1' COMMENT '连续签到天数'", nullable = false)
    private Integer continueSignDay;

    @Column(name = "continue_sign_point", columnDefinition = "int unsigned DEFAULT '1' COMMENT '连续签到赠送数量'", nullable = false)
    private Integer continueSignPoint;

    @Column(name = "consume_per_point", columnDefinition = "decimal(10,2) default '0' COMMENT '每消费多少元获取1个点'", nullable = false)
    private BigDecimal consumePerPoint;

    @Column(name = "low_order_amount", columnDefinition = "decimal(10,2) default '0' COMMENT '最低获取点数的订单金额'", nullable = false)
    private BigDecimal lowOrderAmount;

    @Column(name = "max_point_per_order", columnDefinition = "int unsigned DEFAULT '1' COMMENT '每笔订单最高获取点数'", nullable = false)
    private Integer maxPointPerOrder;

    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '类型：0->积分规则；1->成长值规则'", nullable = false)
    private Integer type;

}