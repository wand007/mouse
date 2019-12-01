package com.mouse.api.dao.entity.resource;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author ; lidongdong
 * @Description 商品对应规格表值表实体
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_goods_specification")
public class GoodsSpecificationEntity implements Serializable {

    private static final long serialVersionUID = -3918891835792440163L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "goods_id", columnDefinition = "int unsigned default '0' COMMENT '商品Id'", nullable = false)
    private Integer goodsId;
    @Column(name = "specification_id", columnDefinition = "int unsigned default '0' COMMENT '规范Id'", nullable = false)
    private Integer specificationId;
    @Column(name = "value", columnDefinition = "varchar(255) COMMENT '规范说明'", nullable = false)
    private String value;
    @Column(name = "pic_url", columnDefinition = "varchar(255) COMMENT '规范图片'", nullable = false)
    private String picUrl;
    @Column(name = "merchant_id", columnDefinition = "varchar(32) COMMENT '商户id'", nullable = false)
    private String merchantId;


}
