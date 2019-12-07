package com.mouse.api.dao.entity.oms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 订单操作历史记录
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "oms_order_operate_history")
public class OmsOrderOperateHistoryEntity implements Serializable {
    private static final long serialVersionUID = -5463149499780535524L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", columnDefinition = "bigint(20) unsigned COMMENT '订单id'", nullable = false)
    private Long orderId;

    @Column(name = "operate_man", columnDefinition = "varchar(64) COMMENT '操作人：用户；系统；后台管理员'", nullable = false)
    private String operateMan;

    @Column(name = "order_status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单'", nullable = false)
    private Integer orderStatus;

    @Column(name = "note", columnDefinition = "varchar(64) COMMENT '备注'", nullable = false)
    private String note;

}