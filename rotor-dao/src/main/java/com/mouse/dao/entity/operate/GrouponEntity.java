package com.mouse.dao.entity.operate;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 拼团表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_groupon")
public class GrouponEntity implements Serializable {
    private static final long serialVersionUID = 1260459463025072240L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '关联的订单ID'")
    private Integer orderId;

    @Column(name = "groupon_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '参与的团购ID，仅当user_type不是1'")
    private Integer grouponId;

    @Column(name = "rules_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '团购规则ID，关联tbl_groupon_rules表ID字段'")
    private Integer rulesId;

    @Column(name = "user_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '用户ID'")
    private Integer userId;

    @Column(name = "creator_user_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '创建者ID'")
    private Integer creatorUserId;


    @Column(name = "share_url", columnDefinition = "varchar(500) COMMENT '团购分享图片地址'", nullable = false)
    private String shareUrl;

    @Column(name = "payed", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否已经支付'", nullable = false)
    private Boolean payed;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

   @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除 0 未删除，1 删除'", nullable = false)
    private Boolean deleted;
}