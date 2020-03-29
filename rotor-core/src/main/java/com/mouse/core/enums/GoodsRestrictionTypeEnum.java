package com.mouse.core.enums;

import com.mouse.core.base.BusinessException;
import lombok.Getter;
import lombok.ToString;

/**
 * @author ; lidongdong
 * @Description 商品限制类型
 * @Date 2020-01-12
 */
@Getter
@ToString
public enum GoodsRestrictionTypeEnum {


    GOODS_TYPE_ALL(0, "全商品限制"),
    GOODS_TYPE_CATEGORY(1, "类目限制"),
    GOODS_TYPE_ARRAY(2, "商品限制"),
    ;

    private int code;
    private String desc;

    GoodsRestrictionTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static GoodsRestrictionTypeEnum parse(int val) {
        GoodsRestrictionTypeEnum[] values = GoodsRestrictionTypeEnum.values();
        for (GoodsRestrictionTypeEnum anEnum : values) {
            if (anEnum.getCode() == val) {
                return anEnum;
            }
        }
        throw new BusinessException("没有找到此状态 : " + val);
    }
}
