package com.mouse.api.commons.req;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ; lidongdong
 * @Description 订单售后表
 * @Date 2019-11-26
 */
@Data
public class SaveAftersaleReq implements Serializable {

    private static final long serialVersionUID = 4100876196190607919L;
    /**
     * 用户ID
     */
    private String orderId;

    /**
     * 售后类型，0是未收货退款，1是已收货（无需退货）退款，2用户退货退款
     * com.mouse.core.enums.AftersaleTypeEnum
     */
    private Integer type;

    /**
     * 退款原因
     */
    private String reason;

    /**
     * 退款金额
     */
    private BigDecimal amount;

    /**
     * 退款凭证图片链接数组
     */
    private String[] pictures;

    /**
     * 退款说明
     */
    private String comment;
    /**
     * 售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消
     * com.mouse.core.enums.AftersaleStatusEnum
     */
    private Integer status;
}