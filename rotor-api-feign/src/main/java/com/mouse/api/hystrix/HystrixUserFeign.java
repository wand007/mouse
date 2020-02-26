package com.mouse.api.hystrix;

import com.mouse.api.feign.UserFeign;
import com.mouse.core.base.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ; lidongdong
 * @Description 用户服务 熔断器
 * @Date 2019-11-30
 */
@Slf4j
@Component
public class HystrixUserFeign implements FallbackFactory<UserFeign> {
    @Override
    public UserFeign create(Throwable throwable) {

        log.error("错误信息：", throwable);
        return new UserFeign() {
            @Override
            public R index(String userId) {
                return null;
            }
        };
    }
}
