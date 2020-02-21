package com.mouse;

import com.mouse.api.config.RedisTemplateConfig;
import com.mouse.api.config.ThreadPoolConfig;
import com.mouse.core.config.WxPrefixConfig;
import com.mouse.core.utils.SecurityProperties;
import com.mouse.core.wx.WXJSPayConfig;
import com.mouse.core.wx.WxJsPayCommon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */

@EnableAsync
@EnableDiscoveryClient
@SpringBootApplication
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    /**
     * redisTemplate
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new RedisTemplateConfig(redisConnectionFactory).getRedisTemplate();
    }

    /**
     * 自定义Async线程池
     *
     * @return
     */
    @Bean
    public AsyncTaskExecutor taskExecutor() {
        return new ThreadPoolConfig(50, 100, 1000, 10,
                "rotor-api-thread-pool").getThreadPool();
    }

    /**
     * 秘钥配置文件
     *
     * @return
     */
    @Bean("apiProperties")
    public SecurityProperties securityProperties() {
        return new SecurityProperties("/hvyogo/data/secret/api-service.properties");
    }



    /**
     * 微信JsPay 操作类
     *
     * @param securityProperties
     * @return
     */
    @Bean
    @DependsOn("apiProperties")
    public WxJsPayCommon wxJsPayCommon(SecurityProperties securityProperties) {
        WXJSPayConfig wxjsPayConfig = new WXJSPayConfig(securityProperties.getValueByKey(WxPrefixConfig.WX_JS_APP_ID)
                , securityProperties.getValueByKey(WxPrefixConfig.WX_JS_MCH_ID)
                , securityProperties.getValueByKey(WxPrefixConfig.WX_JS_KEY)
                , "47.95.235.88"
                , securityProperties.getValueByKey(WxPrefixConfig.WX_JS_CERT_PATH)
                , securityProperties.getValueByKey(WxPrefixConfig.WX_JS_PAY_NOTIFY_URL));
        return new WxJsPayCommon(wxjsPayConfig);
    }
}
