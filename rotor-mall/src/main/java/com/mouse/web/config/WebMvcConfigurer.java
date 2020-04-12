package com.mouse.web.config;

import com.mouse.web.interceptor.SafeInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

/**
 * @author ; lidongdong
 * @Description 定义需要拦截的路径
 * @Date 2019-12-13
 */
@Slf4j
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {

    @Autowired
    SafeInterceptor safeInterceptor;


    /**
     * 拦截器 跳过接口权限校验(包括和上面两个和登出接口)
     */
    public static final List<String> WHITE_URL_03 = Arrays.asList(
            "/MP_verify_H0CfESnNrbjG7kkx.txt",
            "/MP_verify_pm29nsQheolO8Q4e.txt",
            "/auth/login",
            "/auth/register",
            "/auth/login_by_weixin"
    );


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(safeInterceptor)
                //指定路径，只拦截此路径
                .addPathPatterns("/**")
                //排除此路径，其他都拦截
                .excludePathPatterns(WHITE_URL_03);
        //如果两个同时存在，排除生效，指定的路径会拦截两次
        //添加拦截器
        super.addInterceptors(registry);
    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //需要配置1：----------- 需要告知系统，这是要被当成静态文件的！
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

}
