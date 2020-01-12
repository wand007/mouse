package com.mouse.core.enums;

import com.mouse.core.base.BusinessException;
import lombok.Getter;
import lombok.ToString;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2020-01-12
 */
@Getter
@ToString
public enum CommentTypeEnum {


    PRODUCT_TYPE(0, "商品评论"),
    SPECIAL_TYPE(1, "专题评论"),
    GOODS_TYPE(2, "订单商品评论"),
    ;

    private int code;
    private String desc;

    CommentTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CommentTypeEnum parse(int val) {
        CommentTypeEnum[] values = CommentTypeEnum.values();
        for (CommentTypeEnum anEnum : values) {
            if (anEnum.getCode() == val) {
                return anEnum;
            }
        }
        throw new BusinessException("没有找到此状态 : " + val);
    }
}
