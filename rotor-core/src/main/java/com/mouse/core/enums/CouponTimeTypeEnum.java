package com.mouse.core.enums;

import com.mouse.core.base.BusinessException;
import lombok.Getter;
import lombok.ToString;

/**
 * @author ; lidongdong
 * @Description 优惠券有效时间限制类型枚举类型
 * @Date 2020-02-03
 */
@Getter
@ToString
public enum CouponTimeTypeEnum {


    TIME_TYPE_DAYS(0, "领取时间的有效天数days"),
    TIME_TYPE_TIME(1, "优惠券有效期");

    private int code;
    private String desc;

    public static CouponTimeTypeEnum parse(int val) {
        CouponTimeTypeEnum[] values = CouponTimeTypeEnum.values();
        for (CouponTimeTypeEnum anEnum : values) {
            if (anEnum.getCode() == val) {
                return anEnum;
            }
        }
        throw new BusinessException("没有找到此状态 : " + val);
    }

    CouponTimeTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }}
