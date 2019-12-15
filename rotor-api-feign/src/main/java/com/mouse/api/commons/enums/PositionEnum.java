package com.mouse.api.commons.enums;

import com.mouse.core.base.BusinessException;
import lombok.Getter;
import lombok.ToString;

/**
 * @author ; lidongdong
 * @Description 广告位置
 * @Date 2019-12-15
 */
@Getter
@ToString
public enum PositionEnum {


    HOME(1,"首页"),
    ;

    private int code;
    private String desc;

    PositionEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PositionEnum parse(int code) {
        PositionEnum[] values = PositionEnum.values();
        for (PositionEnum value : values) {
            if (code == value.getCode()) {
                return value;
            }
        }
        throw new BusinessException("没有找到此状态 : " + code);
    }

}
