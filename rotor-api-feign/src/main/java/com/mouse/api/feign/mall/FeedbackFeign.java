package com.mouse.api.feign.mall;

import com.mouse.api.commons.req.FeedbackReq;
import com.mouse.api.hystrix.mall.HystrixFeedbackfeign;
import com.mouse.core.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ; lidongdong
 * @Description 意见反馈服务 API
 * @Date 2020-01-25
 */
@FeignClient(name = "http://ROTOR-API",
        path = "rotor-api/wx/feedback",
        fallbackFactory = HystrixFeedbackfeign.class)
public interface FeedbackFeign {
    /**
     * 添加意见反馈
     *
     * @param userId 用户ID
     * @param param  意见反馈
     * @return 操作结果
     */
    @PostMapping("submit")
    R submit(@RequestParam(name = "userId") String userId,
             @RequestBody FeedbackReq param);
}
