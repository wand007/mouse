package com.mouse.core.base;

import lombok.Getter;

/**
 * @author ; lidongdong
 * @Description 自定义状态码
 * @Date 2019-11-30
 */
@Getter
public enum BusinessCode {


    /**
     * 成功状态码标志, 请求成功后必须返回该状态码
     * 可在代码中直接使用 SUCCESS, 如果有特殊需要, 自己定义状态码
     * 自己定义方式: COMMON_SUCCESS_XXX(I, "某某某")
     */
    SUCCESS(10000, "操作成功"),
    SUCCESS_PART(10001, "部分成功"),


    /**
     * 此状态码前端直接提示
     */
    ALERT_MESSAGE(12000, "前端直接提示的 指导用户进一步操作的信息"),

    ERROR_PARAMS(400201, "参数非法"),
    /**
     * 基础失败状态码标志
     */
    ERROR(50000, "操作已受理,请稍后再试"),

    REQUEST_ERROR(50001, "请求失败,请稍后再试"),
    REQUEST_SIGN_ERROR(50002, "签名失败"),
    REQUEST_TIMESTAMP_ERROR(50003, "时间戳异常"),
    REQUEST_IP_ERROR(50004, "ip不允许访问"),
    ERROR_SERVER(50005, "服务未知异常稍后再试"),

    ;

    private int code;
    private String desc;


    BusinessCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public static BusinessCode parse(int code) {
        BusinessCode[] values = BusinessCode.values();

        for (BusinessCode value : values) {
            if (code == value.getCode()) {
                return value;
            }
        }

        throw new BusinessException("没找到此业务代码 : " + code);
    }
}
