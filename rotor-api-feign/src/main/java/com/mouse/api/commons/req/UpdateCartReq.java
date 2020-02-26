package com.mouse.api.commons.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 购物车商品表
 * @Date 2019-11-26
 */
@Data
public class UpdateCartReq implements Serializable {

    private static final long serialVersionUID = 3874692096982638365L;
    /**
     * 用户表的用户ID
     */
    private String userId;

    /**
     * 商品货品表的货品ID
     */
    private Integer productId;

    /**
     * 商品货品的数量
     */
    private Short number;

    /**
     * 购物车中商品是否选择状态
     */
    private Boolean isChecked;

}