package com.mouse.core.config;

/**
 * @author ; lidongdong
 * @Description redis相关配置
 * @Date 2020-01-05
 */
public class RotorRedisConfig {


    public static final class LockPrefix {
        /**
         * 定时刷新token 定时任务锁
         */
        public static final String HVYOSV_TASK_REFESH_TOKEN_LOCK = "HVYOSV_TASK_REFESH_TOKEN_LOCK";
    }
}
