package com.mouse.api.hystrix.mall;

import com.mouse.api.commons.enums.RefererEnum;
import com.mouse.api.commons.req.wx.WxLoginInfo;
import com.mouse.api.feign.mall.AuthFeign;
import com.mouse.core.base.BusinessCode;
import com.mouse.core.base.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ; lidongdong
 * @Description 鉴权服务 熔断器
 * @Date 2019-11-30
 */
@Slf4j
@Component
public class HystrixAuthFeign implements FallbackFactory<AuthFeign> {
    @Override
    public AuthFeign create(Throwable throwable) {

        log.error("错误信息：", throwable);
        return new AuthFeign() {
            @Override
            public R login(String username, String password, RefererEnum referer, String userAgent, String landingIP) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R loginByWeiXin(WxLoginInfo wxLoginInfo) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R registerCaptcha(String mobile) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R register(String username, String password, String mobile, RefererEnum referer, String userAgent, String lastLoginIp, String code, String wxCode) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R captcha(Integer userId, String type, String mobile) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R reset(Integer userId, String password, String mobile, String code) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R resetPhone(Integer userId, String password, String mobile, String code) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R profile(Integer userId, String password, String mobile, String code) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R bindPhone(Integer userId, String encryptedData, String iv) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R logout(Integer userId) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R info(Integer userId) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }
        };
    }
}
