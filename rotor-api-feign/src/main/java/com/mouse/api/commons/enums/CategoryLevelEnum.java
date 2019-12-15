package com.mouse.api.commons.enums;

import com.mouse.core.base.BusinessException;
import lombok.Getter;
import lombok.ToString;

/**
 * @author ; lidongdong
 * @Description 类目等级
 * @Date 2019-12-15
 */
@Getter
@ToString
public enum CategoryLevelEnum {

    L1(1, "一级类目"),
    ;

    private int code;
    private String desc;

    CategoryLevelEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CategoryLevelEnum parse(int code) {
        CategoryLevelEnum[] values = CategoryLevelEnum.values();
        for (CategoryLevelEnum value : values) {
            if (code == value.getCode()) {
                return value;
            }
        }
        throw new BusinessException("没有找到此状态 : " + code);
    }
}
