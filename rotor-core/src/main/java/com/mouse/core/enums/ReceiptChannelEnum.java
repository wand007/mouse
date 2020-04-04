package com.mouse.core.enums;

import com.mouse.core.base.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2020-04-04
 */
@Getter
@ToString
@AllArgsConstructor
public enum ReceiptChannelEnum {

    DEFAULT(0, "未知"),

    BANK_CARD(10, "银行卡"),

    ALI_PAY(20, "支付宝"),

    WE_CHAT(30, "微信"),

    WX_PAY_H5(35, "微信H5"),

    WX_PAY_JS(36, "微信JSAPI"),
    ;


    private int code;
    private String desc;


    public static ReceiptChannelEnum parse(int code) {
        for (ReceiptChannelEnum channel : ReceiptChannelEnum.values()) {
            if (channel.code == code) {
                return channel;
            }
        }
        throw new BusinessException("解析收款渠道失败!");
    }
}
