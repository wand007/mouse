package com.mouse.core.enums;

import com.mouse.core.base.BusinessException;
import lombok.Getter;
import lombok.ToString;

/**
 * @author ; lidongdong
 * @Description 团购规则状态
 * @Date 2020-03-29
 */
@Getter
@ToString
public enum GrouponRuleStatusEnum {

    ON(0, "正常上线"),
    DOWN_EXPIRE(1, "到期自动下线"),
    DOWN_ADMIN(2, "管理手动下线");

    private int code;
    private String desc;

    public static GrouponRuleStatusEnum parse(int val) {
        GrouponRuleStatusEnum[] values = GrouponRuleStatusEnum.values();
        for (GrouponRuleStatusEnum anEnum : values) {
            if (anEnum.getCode() == val) {
                return anEnum;
            }
        }
        throw new BusinessException("没有找到此状态 : " + val);
    }

    GrouponRuleStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
