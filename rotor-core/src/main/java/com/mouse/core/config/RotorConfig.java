package com.mouse.core.config;

import com.mouse.core.utils.PropertiesUtils;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2020-01-05
 */
public class RotorConfig {


    public static final class SysPrefix {
        /**
         * 环境前缀
         */
        public static final String ENV_PRE = PropertiesUtils.getProperty("config", "mail.title.pre", "pro");
        /**
         * 开发邮箱
         */
        public static final String[] DEVELOP_MAILS = PropertiesUtils.getProperty("config", "developer.mail", null).split(",");
        /**
         * 报警邮箱
         */
        public static final String[] WARNING_MAIL = PropertiesUtils.getProperty("config", "warning.mail", null).split(",");
        /**
         * 运营邮箱
         */
        public static final String[] RUNNING_MAIL = PropertiesUtils.getProperty("config", "running.mail", null).split(",");


        public static final String SNOWFLAKE_DATE_CENTER_ID = "SNOWFLAKE_DATE_CENTER_ID";

    }

    /**
     * 微信前缀
     */
    public static final class WxPrefix {
        /**
         * 微信token刷新定时任务是否执行标记
         */
        public static final String TOKEN_IGNOE_FLAG_H5 = "TOKEN_IGNOE_FLAG_H5";
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

    public static final class UserPrefix {
        /**
         * 邮件发送时间间隔
         */
        public static final int MAIL_MINUTES = 30;
        /**
         * 邮件发送标记
         */
        public static final String MAIL_SEND_FLAG = "MAIL_SEND_FLAG_";


    }

    /**
     * 登陆相关
     */
    public static final class LoginPrefix {
        /**
         * 用户授权缓存
         */
        public static final String REDIS_TOKEN_AUTH = "S_AUTH:";
        /**
         * 用户常用信息缓存
         */
        public static final String REDIS_TOKEN_USER_INFO = "S_USER_INFO:";
        /**
         * 授权session 过期时间    前端用
         */
        public static final int REDIS_TOKEN_AUTH_EXPIRED_DEFAUT = 7;

    }
}
