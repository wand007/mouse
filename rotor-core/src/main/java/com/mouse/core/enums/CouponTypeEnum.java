package com.mouse.core.enums;

import com.mouse.core.base.BusinessException;
import lombok.Getter;
import lombok.ToString;

/**
 * @author ; lidongdong
 * @Description 优惠券类型枚举类型
 * @Date 2020-02-03
 */
@Getter
@ToString
public enum CouponTypeEnum {

    TYPE_COMMON(0, "优惠券只能领取，不能兑换"),
    TYPE_REGISTER(1, "新用户注册优惠券自动发送"),
    TYPE_CODE(2, "只能兑换");;

    private int code;
    private String desc;

    public static CouponTypeEnum parse(int val) {
        CouponTypeEnum[] values = CouponTypeEnum.values();
        for (CouponTypeEnum anEnum : values) {
            if (anEnum.getCode() == val) {
                return anEnum;
            }
        }
        throw new BusinessException("没有找到此状态 : " + val);
    }

    CouponTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }}
