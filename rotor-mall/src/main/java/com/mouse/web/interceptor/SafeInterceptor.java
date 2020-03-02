package com.mouse.web.interceptor;

import com.mouse.core.base.BusinessCode;
import com.mouse.core.base.R;
import com.mouse.core.config.RotorConfig;
import com.mouse.core.params.RotorSessionUser;
import com.mouse.core.utils.WebKit;
import com.mouse.web.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author ; lidongdong
 * @Description 拦截后preHandle方法实现的操作
 * @Date 2019-12-13
 */
@Slf4j
@Component
public class SafeInterceptor extends BaseController implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        //忽略网络切换
        String userAgent = WebKit.getDeviceInfo(request.getHeader("User-Agent"));
        //获取ip
        String ip = WebKit.getRequestIp(request);
        String uri = request.getRequestURI();

        //授权token
        String authToken = request.getHeader(WebKit.AUTH_HEADER);
        //下载、导出等部分接口无法在header中的值，所以将token放在了请求URL地址中
        if (StringUtils.isBlank(authToken)) {
            authToken = request.getParameter("token");
        }
        if (StringUtils.isBlank(authToken)) {
            log.info("uri:{} 请求未携带登录token \n\tUser-Agent:{},\n\tHeaders:{}", uri, userAgent, WebKit.getHeaders(request));
            WebKit.response(response, R.fromBusinessCode(BusinessCode.ERROR_USER_NOT_LOGIN));
            return false;
        }
        String userId = (String) redisTemplate.opsForValue().get(RotorConfig.LoginPrefix.REDIS_TOKEN_AUTH + authToken);
        if (StringUtils.isBlank(userId)) {
            log.info("uri:{} 请求携带token 未找到对应token ,\n\ttoken:{}\n\tUser-Agent:{}", uri, authToken, userAgent);
            WebKit.response(response, R.fromBusinessCode(BusinessCode.ERROR_USER_NOT_LOGIN));
            return false;
        }

        RotorSessionUser hvyosvSessionUser = (RotorSessionUser) redisTemplate.opsForValue().get(RotorConfig.LoginPrefix.REDIS_TOKEN_USER_INFO + userId);
        if (hvyosvSessionUser == null) {
            log.info("uri:{} 请求携带token 未找到对应session 未找到对应的用户信息,\n\tuserId:{}\n\tUser-Agent:{}", uri, userId, userAgent);
            WebKit.response(response, R.fromBusinessCode(BusinessCode.ERROR_USER_NOT_LOGIN));
            return false;
        }

        if (userAgent != null && !userAgent.equals(hvyosvSessionUser.getUserAgent())) {
            log.info("uri:{} User-Agent异常 \n\ttoken:{}, \n\tUser-Agent:{},\n\tSessionUserAgent:{}", uri, authToken, userAgent, hvyosvSessionUser.getUserAgent());
            WebKit.response(response, R.fromBusinessCode(BusinessCode.ERROR_USER_NOT_LOGIN));
            return false;
        }

        request.setAttribute("sessionUser", hvyosvSessionUser);


        //缓存用户token
        redisTemplate.opsForValue().set(RotorConfig.LoginPrefix.REDIS_TOKEN_AUTH + hvyosvSessionUser.getH5Token(), hvyosvSessionUser.getId(),
                RotorConfig.LoginPrefix.REDIS_TOKEN_AUTH_EXPIRED_DEFAUT, TimeUnit.DAYS);
        //缓存用户登陆信息
        redisTemplate.opsForValue().set(RotorConfig.LoginPrefix.REDIS_TOKEN_USER_INFO + hvyosvSessionUser.getId(),
                hvyosvSessionUser, RotorConfig.LoginPrefix.REDIS_TOKEN_AUTH_EXPIRED_DEFAUT, TimeUnit.DAYS);
        return true;
    }
}
