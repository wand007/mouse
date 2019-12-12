package com.mouse.dao.entity.operate;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 优惠券用户使用表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_coupon_user")
public class CouponUserEntity implements Serializable {
    private static final long serialVersionUID = 665397871246760951L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '用户表的用户ID'", nullable = false)
    private Integer userId;

    @Column(name = "coupon_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '优惠券ID'", nullable = false)
    private Integer couponId;

    @Column(name = "status", columnDefinition = "smallint unsigned DEFAULT '0' COMMENT '使用状态, 如果是0则未使用；如果是1则已使用；如果是2则已过期；如果是3则已经下架'", nullable = false)
    private Short status;

    @Column(name = "used_time", columnDefinition = "datetime COMMENT '使用时间'", nullable = false)
    private LocalDateTime usedTime;

    @Column(name = "start_time", columnDefinition = "datetime COMMENT '有效期开始时间'", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", columnDefinition = "datetime COMMENT '有效期截至时间'", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "order_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '订单ID'")
    private Integer orderId;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

   @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除 0 未删除，1 删除'", nullable = false)
    private Boolean deleted;
}