package com.mouse.core.wx.config;

/**
 * @author ; lidongdong
 * @Description 微信常量
 * @Date 2019-12-31
 */
public class WxConstants {

    public static final String DOMAIN_API = "api.mch.weixin.qq.com";
    public static final String DOMAIN_API2 = "api2.mch.weixin.qq.com";
    public static final String DOMAIN_APIHK = "apihk.mch.weixin.qq.com";
    public static final String DOMAIN_APIUS = "apius.mch.weixin.qq.com";


    public static final String URL_ACCESS_TOKEN = "https://api2.weixin.qq.com/cgi-bin/token";
    public static final String URL_JSAPI_TICKET = "https://api2.weixin.qq.com/cgi-bin/ticket/getticket";

    public static final String URL_USER_ACCESS_TOKEN = "https://api2.weixin.qq.com/sns/oauth2/access_token";
    public static final String URL_USER_ACCESS_TOKEN_REFRESH = "https://api2.weixin.qq.com/sns/oauth2/refresh_token";

    public static final String URL_USER_INFO = "https://api.weixin.qq.com/sns/userinfo";

    public static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/oauth2/authorize";

    public static final String URL_MEDIA_GET = "https://api.weixin.qq.com/cgi-bin/media/get";

    public static final String URL_TEMPLATE_MSG = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    public static final String URL_MINI_JSCODE_2_SESSION = "https://api.weixin.qq.com/sns/jscode2session";


    /**
     * 小程序获取二维码 不限次数 SEND_TEMP_MSG
     */
    public static final String URL_MINI_ACODE_UNLIMITED = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";

    /**
     * 小程序模板消息
     */
    public static final String URL_MINI_SEND_TEMP_MSG = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=";
    /**
     * 授权
     */
    public static final String GRANT_TYPE = "authorization_code";
}
