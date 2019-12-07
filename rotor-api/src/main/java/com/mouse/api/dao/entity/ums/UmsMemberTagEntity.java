package com.mouse.api.dao.entity.ums;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ; lidongdong
 * @Description 用户标签表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "ums_member_tag")
public class UmsMemberTagEntity implements Serializable {
    private static final long serialVersionUID = -3828660273935995755L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '用户标签名称'", nullable = false)
    private String name;

    @Column(name = "finish_order_count", columnDefinition = "int unsigned default '0' COMMENT '自动打标签完成订单数量'", nullable = false)
    private Integer finishOrderCount;

    @Column(name = "finish_order_amount", columnDefinition = "decimal(10,2) default '0' COMMENT '自动打标签完成订单金额'", nullable = false)
    private BigDecimal finishOrderAmount;

}