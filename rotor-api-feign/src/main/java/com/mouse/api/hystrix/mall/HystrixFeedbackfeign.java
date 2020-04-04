package com.mouse.api.hystrix.mall;

import com.mouse.api.commons.req.FeedbackReq;
import com.mouse.api.feign.mall.FeedbackFeign;
import com.mouse.core.base.BusinessCode;
import com.mouse.core.base.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ; lidongdong
 * @Description 意见反馈服务 熔断器
 * @Date 2020-01-25
 */
@Slf4j
@Component
public class HystrixFeedbackfeign implements FallbackFactory<FeedbackFeign> {
    @Override
    public FeedbackFeign create(Throwable throwable) {
        log.error("错误信息：", throwable);
        return new FeedbackFeign() {
            @Override
            public R submit(String userId, FeedbackReq param) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }
        };
    }
}
