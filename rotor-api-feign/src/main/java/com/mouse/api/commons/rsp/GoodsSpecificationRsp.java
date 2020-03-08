package com.mouse.api.commons.rsp;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2020-03-08
 */
@Data
public class GoodsSpecificationRsp implements Serializable {

    private static final long serialVersionUID = 747438094841063218L;
    private Integer id;

    /**
     * 商品表的商品ID
     */
    private Integer goodsId;

    /**
     * 商品规格值列表
     */
    private String specifications;

    /**
     * 商品规格值
     */
    private String value;

    /**
     * 商品规格图片
     */
    private String picUrl;
}
