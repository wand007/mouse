package com.mouse.api.commons.rsp;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ; lidongdong
 * @Description 购物车商品表
 * @Date 2019-11-26
 */
@Data
public class CartRsp implements Serializable {


    private static final long serialVersionUID = 5088313337250378460L;
    private Integer id;
    /**
     * 用户表的用户ID
     */
    private String userId;

    /**
     * 商品表的商品ID
     */
    private Integer goodsId;

    /**
     * 商品编号
     */
    private String goodsSn;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品货品表的货品ID
     */
    private Integer productId;

    /**
     * 商品货品的价格
     */
    private BigDecimal price;

    /**
     * 商品货品的数量
     */
    private Short number;

    /**
     * 商品规格值列表，采用JSON数组格式
     */
    private String specifications;

    /**
     * 购物车中商品是否选择状态
     */
    private Boolean isChecked;

    /**
     * 商品图片或者商品货品图片
     */
    private String picUrl;

}