package com.mouse.dao.entity.operate;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 团购规则表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_groupon_rules")
public class GrouponRulesEntity implements Serializable {
    private static final long serialVersionUID = -5962371053916602235L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "goods_id", columnDefinition = "int unsigned DEFAULT '0' COMMENT '商品表的商品ID'", nullable = false)
    private Integer goodsId;

    @Column(name = "goods_name", columnDefinition = "varchar(127) DEFAULT '[]' COMMENT '商品名称'", nullable = false)
    private String goodsName;

    @Column(name = "pic_url", columnDefinition = "varchar(255) COMMENT '商品图片或者商品货品图片'", nullable = false)
    private String picUrl;

    @Column(name = "discount", columnDefinition = "decimal(10,2) default '0' COMMENT '优惠金额'", nullable = false)
    private BigDecimal discount;

    @Column(name = "discount_member", columnDefinition = "int unsigned DEFAULT '0' COMMENT '达到优惠条件的人数'", nullable = false)
    private Integer discountMember;


    @Column(name = "expire_time", columnDefinition = "datetime  COMMENT '团购过期时间'", nullable = false)
    private LocalDateTime expireTime;

    @Column(name = "\"status\"", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '团购规则状态，正常上线则0，到期自动下线则1，管理手动下线则2'", nullable = false)
    private Integer status;

    @CreationTimestamp
    @Column(name = "add_time", columnDefinition = "datetime  COMMENT '创建时间'", nullable = false)
    private LocalDateTime addTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新时间'", nullable = false)
    private LocalDateTime updateTime;

   @Column(name = "deleted", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '逻辑删除 0 未删除，1 删除'", nullable = false)
    private Boolean deleted;
}