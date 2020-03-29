package com.mouse.core.enums;

import com.mouse.core.base.BusinessException;
import lombok.Getter;
import lombok.ToString;

/**
 * @author ; lidongdong
 * @Description 售后状态
 * @Date 2020-03-29
 */
@Getter
@ToString
public enum AftersaleStatusEnum {

    NONE(0, "开团未支付"),
    ON(1, "开团中"),
    SUCCEED(2, "开团成功"),
    FAIL(3, "开团失败");

    private int code;
    private String desc;

    public static AftersaleStatusEnum parse(int val) {
        AftersaleStatusEnum[] values = AftersaleStatusEnum.values();
        for (AftersaleStatusEnum anEnum : values) {
            if (anEnum.getCode() == val) {
                return anEnum;
            }
        }
        throw new BusinessException("没有找到此状态 : " + val);
    }

    AftersaleStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
