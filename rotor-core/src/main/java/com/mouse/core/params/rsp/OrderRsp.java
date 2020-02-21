package com.mouse.core.params.rsp;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author ; lidongdong
 * @Description 订单查询
 * @Date 2019-11-26
 */
@Data
public class OrderRsp implements Serializable {
    private static final long serialVersionUID = 2886990096108026778L;
    /**
     * 订单ID
     */
    private String id;
    /**
     * 订单编号
     */
    private String orderSn;
    /**
     * 实付费用
     */
    private BigDecimal actualPrice;
    /**
     * 订单状态
     */
    private Integer orderStatus;
    private String orderStatusTex;
    /**
     *
     */
    private String handleOption;
    /**
     * 是否拼团
     */
    private Boolean isGroupin;
    /**
     * 订单商品详情
     */
    private List<OrderGoodsRsp> goodsList;
}