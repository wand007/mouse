package com.mouse.core.wx;

import com.mouse.core.utils.ParamKit;
import com.mouse.core.utils.SecurityKit;
import com.mouse.core.wx.config.WxH5Configuration;
import com.mouse.core.wx.param.WxUserAccessToken;
import com.mouse.core.wx.param.WxUserInfo;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-31
 */
public class WxH5Comm implements Serializable {


    public String appId;

    private String appSecret;

    private String loginSeccessUri;

    private String redirectUri;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getLoginSeccessUri() {
        return loginSeccessUri;
    }

    public void setLoginSeccessUri(String loginSeccessUri) {
        this.loginSeccessUri = loginSeccessUri;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public WxH5Comm(WxH5Configuration configuration) {
        this.appId = configuration.getAppId();
        this.appSecret = configuration.getAppSecret();
        this.redirectUri = configuration.getRedirectUri();
        this.loginSeccessUri = configuration.getLoginSuccessUri();
    }


    /**
     * h5 弹起授权页面
     *
     * @param state 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @throws IOException
     */
    public String authorizeUrl(String state) {
        return WxUtils.h5AuthorizeRedirect(state, appId, redirectUri);
    }

    /**
     * 用户accessToken 获取
     *
     * @param code 登录授权 回调 code
     * @return
     */
    public WxUserAccessToken userAccessToken(String code) {
        return WxUtils.getUserAccessToken(code, appId, appSecret);
    }


    /**
     * 用户accessToken 刷新
     *
     * @param refreshToken 刷新
     * @return
     */
    public WxUserAccessToken userAccessTokenRefresh(String refreshToken) {
        return WxUtils.refreshUserAccessToken(appId, refreshToken);
    }

    /**
     * 用户信息 获取
     *
     * @param userAccessToken
     * @return
     */
    public WxUserInfo userInfo(WxUserAccessToken userAccessToken) {
        return WxUtils.userinfo(userAccessToken.getAccess_token(), userAccessToken.getOpenid());
    }


    /**
     * 刷新token
     */
    public String refreshAccessToken() {
        return WxUtils.accessToken(appId, appSecret);
    }

    /**
     * 刷新token
     */
    public String refreshJsApiToken(String accessToken) {
        return WxUtils.jsapiTicket(accessToken);
    }

    /**
     * 签名
     *
     * @param url
     * @param jsapiTicket
     */
    public Map<String, String> sign(String url,
                                    String jsapiTicket) {
        //设置保护时间20s
        Map<String, String> map = new HashMap<>(8);
        map.put("noncestr", WXPayUtil.generateNonceStr());
        map.put("jsapi_ticket", jsapiTicket);
        map.put("timestamp", System.currentTimeMillis() / 1000 + "");
        map.put("url", url);
        map.put("sign", SecurityKit.sha1(ParamKit.sortFormatParam(map, false)));
        map.put("appId", appId);

        return map;
    }
}
