package com.mouse.api;

import com.mouse.api.config.ThreadPoolConfig;
import com.mouse.api.config.RedisTemplateConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
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
}
