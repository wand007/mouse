package com.mouse.api.dao.entity.ums;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ; lidongdong
 * @Description 会员等级表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "ums_member_level")
public class UmsMemberLevelEntity implements Serializable {
    private static final long serialVersionUID = 7928766383799762268L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '会员等级名称'", nullable = false)
    private String name;

    @Column(name = "growth_point", columnDefinition = "int unsigned COMMENT '所需成长值'", nullable = false)
    private Integer growthPoint;

    @Column(name = "source_type", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否为默认等级：0->不是；1->是'", nullable = false)
    private Integer defaultStatus;

    @Column(name = "free_freight_point", columnDefinition = "decimal(10,2) default '0' COMMENT '免运费标准'", nullable = false)
    private BigDecimal freeFreightPoint;

    @Column(name = "comment_growth_point", columnDefinition = "int unsigned COMMENT '每次评价获取的成长值'", nullable = false)
    private Boolean commentGrowthPoint;

    @Column(name = "priviledge_free_freight", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否有免邮特权'", nullable = false)
    private Boolean priviledgeFreeFreight;

    @Column(name = "priviledge_sign_in", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否有签到特权'", nullable = false)
    private Boolean priviledgeSignIn;

    @Column(name = "priviledge_comment", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否有评论获奖励特权'", nullable = false)
    private Boolean priviledgeComment;

    @Column(name = "priviledge_promotion", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否有专享活动特权'", nullable = false)
    private Boolean priviledgePromotion;

    @Column(name = "priviledge_member_price", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否有会员价格特权'", nullable = false)
    private Boolean priviledgeMemberPrice;

    @Column(name = "priviledge_birthday", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '是否有生日特权'", nullable = false)
    private Boolean priviledgeBirthday;

    @Column(name = "note", columnDefinition = "varchar(64) COMMENT '备注'", nullable = false)
    private String note;
}