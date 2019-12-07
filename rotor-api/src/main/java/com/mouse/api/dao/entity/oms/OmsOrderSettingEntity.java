package com.mouse.api.dao.entity.oms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 订单设置表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "oms_order_setting")
public class OmsOrderSettingEntity implements Serializable {
    private static final long serialVersionUID = -5104301090875869475L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flash_order_overtime", columnDefinition = "int unsigned COMMENT '秒杀订单超时关闭时间(分)'", nullable = false)
    private Integer flashOrderOvertime;

    @Column(name = "normal_order_overtime", columnDefinition = "int unsigned COMMENT '正常订单超时时间(分)'", nullable = false)
    private Integer normalOrderOvertime;

    @Column(name = "confirm_overtime", columnDefinition = "int unsigned COMMENT '发货后自动确认收货时间（天）'", nullable = false)
    private Integer confirmOvertime;

    @Column(name = "finish_overtime", columnDefinition = "int unsigned COMMENT '自动完成交易时间，不能申请售后（天）'", nullable = false)
    private Integer finishOvertime;

    @Column(name = "comment_overtime", columnDefinition = "int unsigned COMMENT '订单完成后自动好评时间（天）'", nullable = false)
    private Integer commentOvertime;

}