package com.mouse.api.feign;

import com.mouse.api.hystrix.HystrixHomeFeign;
import com.mouse.core.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ; lidongdong
 * @Description 首页服务feign
 * @Date 2019-12-15
 */
@FeignClient(name = "http://ROTOR-API",
        path = "rotor-api/home",
        fallbackFactory = HystrixHomeFeign.class)
public interface HomeFeign {

    /**
     * 首页数据
     * 当用户已经登录时，非空。为登录状态为null
     *
     * @param userId 登陆用户ID
     * @return
     */
    @ResponseBody
    @GetMapping(value = "index")
    public R index(@RequestParam(value = "userId", required = false) String userId);
}

