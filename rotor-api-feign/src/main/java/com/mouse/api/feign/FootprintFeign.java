package com.mouse.api.feign;

import com.mouse.api.hystrix.HystrixFootprintFeign;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author ; lidongdong
 * @Description 用户访问足迹服务 feign
 * @Date 2019-12-15
 */
@FeignClient(name = "http://ROTOR-API",
        path = "rotor-api/footprint",
        fallbackFactory = HystrixFootprintFeign.class)
public interface FootprintFeign {

}

