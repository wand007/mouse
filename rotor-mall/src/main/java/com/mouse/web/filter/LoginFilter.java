package com.mouse.web.filter;

import com.mouse.core.base.BodyReaderHttpServletRequestWrapper;
import com.mouse.core.base.MediaType;
import com.mouse.core.enums.RequestMethodEunm;
import com.mouse.core.utils.WebKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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

    private CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("登录过滤器初始化");
    }


    /**
     * 不打印参数
     */
    private static final Set<String> notPrintParamUrls = new HashSet<String>() {
        {
            add("/naturalPerson/uploadIdCard");
        }
    };

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //设置跨域
        if (StringUtils.isNotBlank(request.getHeader("Origin"))) {
            if (request.getHeader("Origin").contains("rotor.com")
                    || request.getHeader("Origin").contains("localhost") || request.getHeader("Origin").contains("127.0.0.1")) {
                WebKit.setCORS(response, request.getHeader("Origin"));
            }
        }
        if (RequestMethodEunm.OPTIONS.getMethod().equals(request.getMethod())) {
            //浏览器对复杂跨域请求的预请求，试探服务器响应是否正确，跳过
            filterChain.doFilter(request, response);
            return;
        }
        //请求路径
        String servletPath = request.getServletPath();
        //静态资源放行
        if (servletPath.endsWith(".txt")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String contentType = request.getContentType();
        if (StringUtils.isNotBlank(contentType) && contentType.contains(MediaType.MULTIPART_FORM_DATA_VALUE)) {
            //multipart/form-data格式一般用于文件类型
            MultipartHttpServletRequest multiReq = multipartResolver.resolveMultipart(request);
            filterChain.doFilter(multiReq, response);
            return;
        }

        BodyReaderHttpServletRequestWrapper requestWrapper = null;
        if (servletRequest instanceof HttpServletRequest) {
            requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
        }

        if (notPrintParamUrls.contains(servletPath)) {
            //不需要打印参数
            WebKit.loggerDefault(requestWrapper, log);
        } else {
            //需要打印参数
            WebKit.loggerSheer(requestWrapper, log);
        }

        filterChain.doFilter(requestWrapper, response);
        return;
    }


    @Override
    public void destroy() {
        log.info("登录过滤器已销毁");
    }
}

