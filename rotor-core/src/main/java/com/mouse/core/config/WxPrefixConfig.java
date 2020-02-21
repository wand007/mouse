package com.mouse.core.config;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2020-02-14
 */
public class WxPrefixConfig {

    /**
     * 微信公众号
     */
    public static final String WX_APP_ID = "WX_APP_ID";
    public static final String WX_APP_SECRET = "WX_APP_SECRET";
    public static final String WX_LOGIN_REDIRECT_URI = "WX_LOGIN_REDIRECT_URI";
    public static final String WX_LOGIN_SUCCESS_URI = "WX_LOGIN_SUCCESS_URI";

    /**
     * 微信JS-支付  参数key
     */
    public static final String WX_JS_APP_ID = "WX_JS_APP_ID";
    public static final String WX_JS_MCH_ID = "WX_JS_MCH_ID";
    public static final String WX_JS_KEY = "WX_JS_KEY";
    //API证书路径
    public static final String WX_JS_CERT_PATH = "WX_JS_CERT_PATH";
    //支付成功回调地址
    public static final String WX_JS_PAY_NOTIFY_URL = "WX_JS_PAY_NOTIFY_URL";
    /**
     * JsPay 授权token
     */
    public static final String WX_ACCESS_TOKEN = "WX_ACCESS_TOKEN";
    public static final String JS_API_TOKEN_REDIS_KEY = "JS_API_TOKEN_REDIS_KEY";
    public static final String LOCK_WX_AUTH_CODE = "LOCK_WX_AUTH_CODE";
}
