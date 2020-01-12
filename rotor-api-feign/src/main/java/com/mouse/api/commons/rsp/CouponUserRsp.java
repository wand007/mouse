package com.mouse.api.commons.rsp;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author ; lidongdong
 * @Description 优惠券用户使用表
 * @Date 2019-11-26
 */
@Data
public class CouponUserRsp implements Serializable {
    private static final long serialVersionUID = -2088176392743610465L;
    /**
     * 优惠券用户使用表ID
     */
    private Integer id;
    /**
     * 优惠券ID
     */
    private Integer cid;
    /**
     * 优惠券名称
     */
    private String name;

    /**
     * 优惠券介绍，通常是显示优惠券使用限制文字
     */
    private String desc;

    /**
     * 优惠券标签，例如新人专用
     */
    private String tag;

    /**
     * 优惠金额
     */
    private BigDecimal discount;

    /**
     * 最少消费金额才能使用优惠券
     */
    private BigDecimal min;

    /**
     * 优惠券状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架
     */
    private Short status;

    /**
     * 使用状态
     */
    private Boolean available;

    /**
     * 有效期开始时间
     */
    private LocalDateTime startTime;

    /**
     * 有效期截至时间
     */
    private LocalDateTime endTime;

}