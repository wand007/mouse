package com.mouse.api.hystrix;

import com.mouse.api.feign.FootprintFeign;
import com.mouse.api.feign.HomeFeign;
import com.mouse.core.base.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ; lidongdong
 * @Description 用户访问足迹服务 熔断器
 * @Date 2019-11-30
 */
@Slf4j
@Component
public class HystrixFootprintFeign implements FallbackFactory<FootprintFeign> {
    @Override
    public FootprintFeign create(Throwable throwable) {

        log.error("错误信息：", throwable);
        return new FootprintFeign() {
        };
    }
}
