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
        /**
         * 同一用户ID最多可创建的收货地址记录数量
         */
        public static final Integer HVYOSV_ADDRESS_MAX = 20;

        /**
         * 同一用户ID最多可创建的银行卡记录数量
         */
        public static final Integer HVYOSV_BANK_CARD_MAX = 20;


    }
}
