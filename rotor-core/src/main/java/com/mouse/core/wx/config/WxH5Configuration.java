package com.mouse.core.wx.config;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-31
 */
@Data
public class WxH5Configuration implements Serializable {

    private static final long serialVersionUID = -2203289730870831329L;

    private String appId;

    private String appSecret;

    /**
     * 授权回调 地址
     */
    private String redirectUri;

    /**
     * 登录
     */
    private String loginSuccessUri;


    public WxH5Configuration(String appId, String appSecret, String redirectUri, String loginSuccessUri) {
        this.appId = appId;
        this.appSecret = appSecret;
        this.redirectUri = redirectUri;
        this.loginSuccessUri = loginSuccessUri;
    }
}
