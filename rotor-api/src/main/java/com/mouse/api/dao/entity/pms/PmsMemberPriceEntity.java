package com.mouse.api.dao.entity.pms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ; lidongdong
 * @Description 商品会员价格表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "pms_member_price")
public class PmsMemberPriceEntity implements Serializable {
    private static final long serialVersionUID = 7990626143726567635L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", columnDefinition = "bigint(20) unsigned COMMENT '商品记录ID'", nullable = false)
    private Long productId;

    @Column(name = "member_level_id", columnDefinition = "bigint(20) unsigned COMMENT '会员等级记录ID'", nullable = false)
    private Long memberLevelId;

    @Column(name = "member_price", columnDefinition = "decimal(10,2) default '0' COMMENT '会员价格'", nullable = false)
    private BigDecimal memberPrice;

    @Column(name = "member_level_name", columnDefinition = "varchar(64) COMMENT '会员等级名称'", nullable = false)
    private String memberLevelName;
}