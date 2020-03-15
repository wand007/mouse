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

    /**
     * 此状态码前端直接提示
     */
    ALERT_MESSAGE(12000, "前端直接提示的 指导用户进一步操作的信息"),

    /**
     * 基础失败状态码标志
     */
    ERROR(50000, "操作已受理,请稍后再试"),

    ERROR_SYS_SERVICE_RESTART(50001, "服务重启中, 请稍候重试"),

    ERROR_SYS_REQUEST_TIMESTAMP(50002, "时间戳异常"),

    ERROR_SYS_PARAMS(50003, "参数非法"),


    //用户相关
    ERROR_USER_NOT_LOGIN(20100, "登录获取更好体验~"),

    ERROR_USER_LOGIN_TIME_OUT(20101, "登录超时"),

    ERROR_USER_NON_EXISTENT(20102, "用户不存在"),

    ERROR_USER_STATUS_ERROR(20103, "用户状态异常"),
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
