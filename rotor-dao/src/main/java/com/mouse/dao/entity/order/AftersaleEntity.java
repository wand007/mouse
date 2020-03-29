package com.mouse.dao.entity.order;

import com.mouse.dao.handler.JsonStringArrayTypeHandler;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 订单售后表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_aftersale")
@org.hibernate.annotations.Table(appliesTo = "tbl_aftersale", comment = "订单售后表")
public class AftersaleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "aftersale_sn", columnDefinition = "varchar(32) COMMENT '售后编号'", nullable = false)
    private String aftersaleSn;

    @Column(name = "order_id", columnDefinition = "varchar(32) COMMENT '用户ID'", nullable = false)
    private String orderId;

    @Column(name = "user_id", columnDefinition = "varchar(32) COMMENT '用户ID'", nullable = false)
    private String userId;
    /**
     * com.mouse.core.enums.AftersaleTypeEnum
     */
    @Column(name = "type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '售后类型，0是未收货退款，1是已收货（无需退货）退款，2用户退货退款'", nullable = false)
    private Integer type;

    @Column(name = "reason", columnDefinition = "varchar(32) COMMENT '退款原因'", nullable = false)
    private String reason;

    @Column(name = "amount", columnDefinition = "decimal(10,2) default '0' COMMENT '退款金额'", nullable = false)
    private BigDecimal amount;

    @Convert(converter = JsonStringArrayTypeHandler.class)
    @Column(name = "pictures", columnDefinition = "varchar(1023) DEFAULT '[]' COMMENT '退款凭证图片链接数组'", nullable = false)
    private String[] pictures;

    @Column(name = "comment", columnDefinition = "varchar(32) COMMENT '退款说明'", nullable = false)
    private String comment;
    /**
     * com.mouse.core.enums.AftersaleStatusEnum
     */
    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消'", nullable = false)
    private Integer status;

    @Column(name = "handle_time", columnDefinition = "datetime  COMMENT '管理员操作时间'")
    private LocalDateTime handleTime;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

    @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除 0 未删除，1 删除'", nullable = false)
    private Boolean deleted;
}