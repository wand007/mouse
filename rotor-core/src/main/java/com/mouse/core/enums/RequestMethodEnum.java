package com.mouse.core.enums;

import com.mouse.core.base.BusinessException;
import lombok.Getter;
import lombok.ToString;

/**
 * @author ; lidongdong
 * @Description HTTP请求方式枚举类型
 * @Date 2019-11-30
 */
@Getter
@ToString
public enum RequestMethodEnum {

    GET("GET", "查询操作"),
    HEAD("HEAD", "HEAD请求只会返回首部的信息，不会返回相应体"),
    POST("POST", "POST方法把数据都存放在body里面"),
    PUT("PUT", "用于改变某些内容"),
    PATCH("PATCH", "这个方法不太常见，是servlet 3.0提供的方法，主要用于更新部分字段"),
    DELETE("DELETE", "删除某些资源"),
    OPTIONS("OPTIONS", "客户端查看服务器端的性能"),
    TRACE("TRACE", "回显服务器的请求");

    private String method;
    private String des;

    RequestMethodEnum(String method, String des) {
        this.method = method;
        this.des = des;
    }

    public static RequestMethodEnum parse(String val) {
        RequestMethodEnum[] values = RequestMethodEnum.values();
        for (RequestMethodEnum temp : values) {
            if (val.equals(temp.getMethod())) {
                return temp;
            }
        }
        throw new BusinessException("没有找到此状态 : " + val);
    }
}