package com.mouse.api.dao.entity.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_product")
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = 2203681737464623383L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "goods_id", columnDefinition = "int unsigned default '0' COMMENT '商品Id'", nullable = false)
    private Integer goodsId;
    @Column(name = "goods_sn", columnDefinition = "varchar(60) COMMENT '商品序列号'", nullable = false)
    private String goodsSn;
    @Column(name = "goods_name", columnDefinition = "varchar(60) COMMENT '产品名称'", nullable = false)
    private String goodsName;
    @Column(name = "market_price", columnDefinition = "decimal(10,2) default '0' COMMENT '市场价'", nullable = false)
    private BigDecimal marketPrice;
    @Column(name = "retail_price", columnDefinition = "decimal(10,2) default '0' COMMENT '零售价格'", nullable = false)
    private BigDecimal retailPrice;
    @Column(name = "goods_number", columnDefinition = "int unsigned default '0' COMMENT '商品库存'", nullable = false)
    private Integer goodsNumber;
    @Column(name = "goods_specification_ids", columnDefinition = "varchar(64) COMMENT '商品规格ids'", nullable = false)
    private String goodsSpecificationIds;
    @Column(name = "specification_Value", columnDefinition = "varchar(64) COMMENT '规格属性组成的字符串，用来显示用'", nullable = false)
    private String specificationValue;
    @Column(name = "group_price", columnDefinition = "decimal(10,2) default '0' COMMENT '团购价格(元)'", nullable = false)
    private BigDecimal groupPrice;
    @Column(name = "merchant_id", columnDefinition = "varchar(32) COMMENT '商户id'", nullable = false)
    private String merchantId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;
}
