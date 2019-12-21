package com.mouse.api.commons.enums;

import com.mouse.core.base.BusinessException;
import lombok.Getter;
import lombok.ToString;

/**
 * @author ; lidongdong
 * @Description 来源枚举类型
 * @Date 2019-11-30
 */
@Getter
@ToString
public enum RefererEnum {

    WX(1, "微信"),
    ;

    private int code;
    private String desc;

    RefererEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static RefererEnum parse(int code) {
        RefererEnum[] values = RefererEnum.values();
        for (RefererEnum value : values) {
            if (code == value.getCode()) {
                return value;
            }
        }
        throw new BusinessException("没有找到此状态 : " + code);
    }

}
