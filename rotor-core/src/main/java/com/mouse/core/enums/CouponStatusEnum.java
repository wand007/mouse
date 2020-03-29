package com.mouse.core.enums;

import com.mouse.core.base.BusinessException;
import lombok.Getter;
import lombok.ToString;

/**
 * @author ; lidongdong
 * @Description 优惠券状态枚举类型
 * @Date 2020-02-03
 */
@Getter
@ToString
public enum CouponStatusEnum {

    STATUS_NORMAL(0, "正常可用"),
    STATUS_EXPIRED(1, "已过期"),
    STATUS_OUT(2, "已下架");

    private int code;
    private String desc;

    public static CouponStatusEnum parse(int val) {
        CouponStatusEnum[] values = CouponStatusEnum.values();
        for (CouponStatusEnum anEnum : values) {
            if (anEnum.getCode() == val) {
                return anEnum;
            }
        }
        throw new BusinessException("没有找到此状态 : " + val);
    }

    CouponStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }}
