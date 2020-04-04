package com.mouse;

import com.mouse.core.config.RedisTemplateConfig;
import com.mouse.core.config.ThreadPoolConfig;
import com.mouse.core.utils.SecurityProperties;
import com.mouse.core.wx.WxH5Comm;
import com.mouse.core.wx.config.WxH5Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import static com.mouse.core.config.RotorConfig.WxPrefix.*;


/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@EnableAsync
@EnableScheduling
@EnableDiscoveryClient
@EnableFeignClients
@ServletComponentScan(basePackages = {"com.mouse.web.filter"})
@SpringBootApplication
public class MallApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
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
                "rotor-mall-thread-pool").getThreadPool();
    }

    /**
     * 秘钥配置文件
     *
     * @return
     */
    @Bean("mallProperties")
    public SecurityProperties securityProperties() {
        return new SecurityProperties("/mouse/data/secret/rotor-mall.properties");
    }

    /**
     * 微信公众号 操作类
     *
     * @param securityProperties
     * @return
     */
    @Bean
    @DependsOn("mallProperties")
    public WxH5Comm wxH5Comm(SecurityProperties securityProperties) {
        WxH5Configuration wxH5Configuration = new WxH5Configuration(securityProperties.getValueByKey(WX_APP_ID),
                securityProperties.getValueByKey(WX_APP_SECRET),
                securityProperties.getValueByKey(WX_LOGIN_REDIRECT_URI),
                securityProperties.getValueByKey(WX_LOGIN_SUCCESS_URI));
        return new WxH5Comm(wxH5Configuration);
    }
}