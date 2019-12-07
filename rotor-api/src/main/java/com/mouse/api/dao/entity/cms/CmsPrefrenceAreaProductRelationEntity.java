package com.mouse.api.dao.entity.cms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 优选专区和产品关系表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "cms_prefrence_area_product_relation")
public class CmsPrefrenceAreaProductRelationEntity implements Serializable {
    private static final long serialVersionUID = 9060426784597251893L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prefrence_area_id", columnDefinition = "bigint(20) unsigned COMMENT '优选专区记录ID'", nullable = false)
    private Long prefrenceAreaId;

    @Column(name = "product_id", columnDefinition = "bigint(20) unsigned COMMENT '商品记录ID'", nullable = false)
    private Long productId;

}