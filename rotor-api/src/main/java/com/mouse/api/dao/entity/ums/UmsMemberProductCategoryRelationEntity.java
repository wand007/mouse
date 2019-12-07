package com.mouse.api.dao.entity.ums;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 会员与产品分类关系表（用户喜欢的分类）
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "ums_member_product_category_relation")
public class UmsMemberProductCategoryRelationEntity implements Serializable {
    private static final long serialVersionUID = 8008419545783645674L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", columnDefinition = "bigint(20) unsigned COMMENT '会员ID'", nullable = false)
    private Long memberId;

    @Column(name = "product_category_id", columnDefinition = "bigint(20) unsigned COMMENT '商品分类id'", nullable = false)
    private Long productCategoryId;

}