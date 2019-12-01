package com.mouse.core.base;

import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author ; lidongdong
 * @Description 通用返回对象
 * @Date 2019-11-30
 */
@ToString
public class R implements Serializable {
    private static final long serialVersionUID = 5588681646938067420L;


    private int statusCode;
    private String statusText;
    private Object data = new HashMap<>();
    private Long currentTimeMillis;


    public R() {
        this.statusCode = BusinessCode.SUCCESS.getCode();
        this.statusText = "success";
        this.currentTimeMillis = System.currentTimeMillis();
    }

    public R(int statusCode, String statusText, Object data, Long currentTimeMillis) {
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.data = data;
        this.currentTimeMillis = currentTimeMillis;
    }

    public R(int statusCode, String statusText) {
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.currentTimeMillis = System.currentTimeMillis();
    }

    public static R fromBusinessCode(BusinessCode businessCode) {
        return new R(businessCode.getCode(), businessCode.getDesc());
    }

    public static R success(Object data) {
        return new R(BusinessCode.SUCCESS.getCode(), "success", data, System.currentTimeMillis());
    }

    public static R success() {
        return new R(BusinessCode.SUCCESS.getCode(), "success", "{}", System.currentTimeMillis());
    }

    public static R error() {
        return new R(BusinessCode.ERROR.getCode(), BusinessCode.ERROR.getDesc(), "{}", System.currentTimeMillis()
        );
    }


    public int getStatusCode() {
        return statusCode;
    }

    public R setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getStatusText() {
        return statusText;
    }

    public R setStatusText(String statusText) {
        this.statusText = statusText;
        return this;
    }

    public Object getData() {
        return data;
    }

    public R setData(Object data) {
        this.data = data;
        return this;
    }

    public Long getCurrentTimeMillis() {
        return currentTimeMillis;
    }

    public void setCurrentTimeMillis(Long currentTimeMillis) {
        this.currentTimeMillis = currentTimeMillis;
    }


}
