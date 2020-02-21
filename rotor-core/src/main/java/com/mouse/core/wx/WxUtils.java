package com.mouse.core.wx;

import com.google.common.base.Charsets;
import com.mouse.core.base.BusinessException;
import com.mouse.core.utils.HttpClientNotProxy;
import com.mouse.core.utils.JsonUtils;
import com.mouse.core.wx.config.WxConstants;
import com.mouse.core.wx.param.WxUserAccessToken;
import com.mouse.core.wx.param.WxUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-31
 */
public class WxUtils {

    static Logger logger = LoggerFactory.getLogger(WxUtils.class);


    /**
     * 弹起授权
     *
     * @param state       回调参数
     * @param appId       appid
     * @param redirectUri
     * @throws IOException
     */
    public static String h5AuthorizeRedirect(String state,
                                             String appId,
                                             String redirectUri) {

        try {
            redirectUri = URLEncoder.encode(redirectUri, "utf-8");
        } catch (Exception e) {
            logger.error("URL encode error ", e);
        }

        StringBuffer sb = new StringBuffer(WxConstants.URL_AUTHORIZE);
        sb.append("?appid=").append(appId)
                .append("&redirect_uri=").append(redirectUri)
                .append("&response_type=").append("code")
                .append("&scope=").append("snsapi_userinfo")
                .append("&state=").append(state)
                .append("&connect_redirect=1")
                .append("#wechat_redirect");
        return sb.toString();
    }


    /**
     * 通过code换取网页授权access_token  注意这个code 使用点击确认登录时获得
     *
     * @param code
     * @param appId
     * @param appSecret
     * @return
     */
    public static WxUserAccessToken getUserAccessToken(String code,
                                                       String appId,
                                                       String appSecret) {
        StringBuffer sb = new StringBuffer(WxConstants.URL_USER_ACCESS_TOKEN);
        sb.append("?appid=").append(appId)
                .append("&secret=").append(appSecret)
                .append("&code=").append(code)
                .append("&grant_type=")
                .append("authorization_code");

        String result = null;
        try {
            result = HttpClientNotProxy.get(sb.toString());
            Map<String, String> jsonMap = JsonUtils.toObject(result, Map.class);
            if (null != jsonMap && null != jsonMap.get("openid")) {
                return JsonUtils.toObject(result, WxUserAccessToken.class);
            }
        } catch (IOException e) {
            logger.error("微信授权异常!  ", e);
            throw new BusinessException("微信授权异常");
        }

        logger.error("微信授权失败!  " + result);
        throw new BusinessException("微信授权失败");
    }

    /**
     * @param appId
     * @param refreshToken
     * @return
     */
    public static WxUserAccessToken refreshUserAccessToken(String appId,
                                                           String refreshToken) {
        StringBuffer sb = new StringBuffer(WxConstants.URL_USER_ACCESS_TOKEN_REFRESH);
        sb.append("?appid=").append(appId)
                .append("&grant_type=refresh_token")
                .append("&refresh_token=").append(refreshToken);

        String s = null;
        try {
            s = HttpClientNotProxy.get(sb.toString());
            Map<String, String> jsonMap = JsonUtils.toObject(s, Map.class);
            if (null != jsonMap && null != jsonMap.get("openid")) {
                return JsonUtils.toObject(s, WxUserAccessToken.class);
            }
        } catch (IOException e) {
            logger.error("微信授权异常!  ", e);
            throw new BusinessException("微信授权异常");
        }

        logger.error("微信授权失败!  " + s);
        throw new BusinessException("微信授权异常");
    }


    /**
     * 获取用户的信息
     *
     * @param userAccessToken
     * @param openId
     * @return
     */
    public static WxUserInfo userinfo(String userAccessToken, String openId) {
        StringBuffer sb = new StringBuffer(WxConstants.URL_USER_INFO);
        sb.append("?access_token=").append(userAccessToken)
                .append("&openid=").append(openId)
                .append("&lang=").append("zh_CN");
        String s = null;
        try {
            s = HttpClientNotProxy.get(sb.toString());
            Map<String, Object> jsonMap = JsonUtils.toObject(s, Map.class);
            if (null != jsonMap && null != jsonMap.get("openid")) {
                return JsonUtils.toObject(s, WxUserInfo.class);
            }
        } catch (IOException e) {
            logger.error("获取用户的信息异常!", e);
            throw new BusinessException("获取用户的信息异常");
        }

        logger.error("获取用户的信息!" + s);
        throw new BusinessException("微信授权异常");
    }


    /**
     * 系统级别 AccessToken
     *
     * @return
     */
    public static String accessToken(String appId,
                                     String appSecret) {
        StringBuffer sb = new StringBuffer(WxConstants.URL_ACCESS_TOKEN);
        sb.append("?grant_type=").append("client_credential")
                .append("&appid=").append(appId)
                .append("&secret=").append(appSecret);

        //从新拉取授权
        String s = null;
        try {
            s = HttpClientNotProxy.get(sb.toString());
            logger.info("刷新AccessToke" + s);
            Map<String, String> jsonMap = JsonUtils.toObject(s, Map.class);
            if (null != jsonMap
                    && null != jsonMap.get("access_token")) {
                return jsonMap.get("access_token");
            }
        } catch (IOException e) {
            logger.error("刷新AccessToken异常!", e);
            throw new BusinessException("刷新AccessToken异常");
        }

        logger.error("刷新AccessToken失败!" + s);
        throw new BusinessException("刷新AccessToken失败");
    }

    /**
     * 刷新AccessToken
     *
     * @return
     */
    public static String jsapiTicket(String accessToken) {
        StringBuffer sb = new StringBuffer(WxConstants.URL_JSAPI_TICKET);
        sb.append("?access_token=").append(accessToken)
                .append("&type=").append("jsapi");

        //从新拉取授权
        String s = null;
        try {
            s = HttpClientNotProxy.get(sb.toString());
            logger.info("jsapiTicket获取" + s);
            Map<String, String> jsonMap = JsonUtils.toObject(s, Map.class);
            if (null != jsonMap
                    && null != jsonMap.get("ticket")) {
                return jsonMap.get("ticket");
            }

        } catch (IOException e) {
            logger.error("刷新ticket失败!", e);
            throw new BusinessException("刷新ticket失败");
        }
        logger.error("刷新ticket异常!" + s);
        throw new BusinessException("刷新ticket异常");
    }

    /**
     * 组装签名的字段
     *
     * @param params     参数
     * @param urlEncoder 是否urlEncoder
     * @return String
     */
    public static String sortFormatParam(Map<String, String> params, boolean urlEncoder) {
        // 先将参数以其参数名的字典序升序进行排序
        TreeMap<String, String> sortedParams = new TreeMap<String, String>(params);
        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> param : sortedParams.entrySet()) {
            String value = param.getValue();
            if (StringUtils.isBlank(value)) {
                continue;
            }
            if (first) {
                first = false;
            } else {
                sb.append("&");
            }
            sb.append(param.getKey()).append("=");
            if (urlEncoder) {
                try {
                    value = urlEncode(value);
                } catch (UnsupportedEncodingException e) {
                }
            }
            sb.append(value);
        }
        return sb.toString();
    }

    /**
     * urlEncode
     *
     * @param src 微信参数
     * @return String
     * @throws UnsupportedEncodingException 编码错误
     */
    public static String urlEncode(String src) throws UnsupportedEncodingException {
        return URLEncoder.encode(src, Charsets.UTF_8.name()).replace("+", "%20");
    }
}
