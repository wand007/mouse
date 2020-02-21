package com.mouse.core.enums;

import com.mouse.core.base.BusinessException;
import lombok.Getter;
import lombok.ToString;

/**
 * @author ; lidongdong
 * @Description 团购状态
 * @Date 2020-02-03
 */
@Getter
@ToString
public enum CouponUserEnum {
    STATUS_USABLE(0, "可用"),
    STATUS_USED(1, "已使用"),
    STATUS_EXPIRED(2, "已过期"),
    STATUS_OUT(3, "已下架"),

    ;

    private int code;
    private String desc;

    public static CouponUserEnum parse(int val) {
        CouponUserEnum[] values = CouponUserEnum.values();
        for (CouponUserEnum anEnum : values) {
            if (anEnum.getCode() == val) {
                return anEnum;
            }
        }
        throw new BusinessException("没有找到此状态 : " + val);
    }

    CouponUserEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }}
