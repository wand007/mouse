package com.mouse.api.dao.entity.cms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 专题商品关系表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "cms_subject_product_relation")
public class CmsSubjectProductRelationEntity implements Serializable {
    private static final long serialVersionUID = 6271204493157054465L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_id", columnDefinition = "bigint(20) unsigned COMMENT '专题表ID'", nullable = false)
    private Long subjectId;

    @Column(name = "product_id", columnDefinition = "bigint(20) unsigned COMMENT '产品记录ID'", nullable = false)
    private Long productId;
}