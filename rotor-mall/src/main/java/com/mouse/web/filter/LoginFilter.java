package com.mouse.web.filter;

import com.mouse.core.base.BodyReaderHttpServletRequestWrapper;
import com.mouse.core.utils.WebKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-18
 */
@Slf4j
@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("登录过滤器初始化");
    }


    /**
     * 不打印参数
     */
    private static final Set<String> notPrintParamUrls = new HashSet<String>() {
        {
            //登录
            add("/auth/login");
        }
    };
    /**
     * 忽略的接口
     */
    private static final Set<String> ignoreUrls = new HashSet<String>() {
        {
            //健康检查
            add("/actuator/health");
        }
    };

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //设置跨域
        if (StringUtils.isNotBlank(request.getHeader("Origin"))) {
            if (request.getHeader("Origin").contains("localhost") || request.getHeader("Origin").contains("127.0.0.1")
                    || request.getHeader("Origin").contains("rotor.com")) {
                WebKit.setCORS(response, request.getHeader("Origin"));
            }
        }
        if (HttpMethod.OPTIONS.name().equals(request.getMethod())) {
            //浏览器对复杂跨域请求的预请求，试探服务器响应是否正确，跳过
            filterChain.doFilter(request, response);
            return;
        }
        //请求路径
        String servletPath = request.getServletPath();
        //忽略的接口
        if (ignoreUrls.contains(servletPath)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        BodyReaderHttpServletRequestWrapper requestWrapper = new BodyReaderHttpServletRequestWrapper(request);

        if (notPrintParamUrls.contains(servletPath)) {
            //不需要打印参数
            WebKit.loggerDefault(requestWrapper, log);
        } else {
            //需要打印参数
            WebKit.loggerSheer(requestWrapper, log);
        }

        filterChain.doFilter(requestWrapper, servletResponse);
        return;
    }


    @Override
    public void destroy() {
        log.info("登录过滤器已销毁");
    }
}

