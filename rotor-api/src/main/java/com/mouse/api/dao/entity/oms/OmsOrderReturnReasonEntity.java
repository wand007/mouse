package com.mouse.api.dao.entity.oms;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 退货原因表
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "oms_order_return_reason")
public class OmsOrderReturnReasonEntity implements Serializable {
    private static final long serialVersionUID = 6760839553113469812L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "varchar(64) COMMENT '退货类型'", nullable = false)
    private String name;

    @Column(name = "sort", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '排序'", nullable = false)
    private Integer sort;

    @Column(name = "status", columnDefinition = "tinyint unsigned DEFAULT '0' COMMENT '状态：0->不启用；1->启用'", nullable = false)
    private Integer status;

}