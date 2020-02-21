package com.mouse.core.params.rsp;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ; lidongdong
 * @Description 订单商品表
 * @Date 2019-11-26
 */
@Data
public class OrderGoodsRsp implements Serializable {


    private static final long serialVersionUID = 4917536115793606760L;
    /**
     * 订单商品表ID
     */
    private Integer id;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品货品的价格
     */
    private BigDecimal price;

    /**
     * 商品货品的数量
     */
    private Short number;

    /**
     * 商品图片或者商品货品图片
     */
    private String picUrl;

}