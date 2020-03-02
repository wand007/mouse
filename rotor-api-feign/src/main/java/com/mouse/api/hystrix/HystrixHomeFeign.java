package com.mouse.api.hystrix;

import com.mouse.api.feign.HomeFeign;
import com.mouse.core.base.BusinessCode;
import com.mouse.core.base.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ; lidongdong
 * @Description 首页服务 熔断器
 * @Date 2019-11-30
 */
@Slf4j
@Component
public class HystrixHomeFeign implements FallbackFactory<HomeFeign> {
    @Override
    public HomeFeign create(Throwable throwable) {

        log.error("错误信息：", throwable);
        return new HomeFeign() {
            @Override
            public R index(String userId) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R about() {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }
        };
    }
}
