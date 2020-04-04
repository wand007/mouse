package com.mouse.api.feign.mall;

import com.mouse.api.hystrix.mall.HystrixFootprintFeign;
import com.mouse.core.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 用户访问足迹服务 feign
 * @Date 2019-12-15
 */
@FeignClient(name = "http://ROTOR-API",
        path = "rotor-api/footprint",
        fallbackFactory = HystrixFootprintFeign.class)
public interface FootprintFeign {

    /**
     * 删除用户足迹
     *
     * @param userId 用户ID
     * @param id     请求内容， { id: xxx }
     * @return 删除操作结果
     */
    @PostMapping("delete")
    R delete(@RequestParam(name = "userId") String userId,
             @RequestParam(name = "id") String id);

    /**
     * 用户足迹列表
     *
     * @param pageNum  分页页数
     * @param pageSize 分页大小
     * @return 用户足迹列表
     */
    @GetMapping("findPage")
    R findPage(@RequestParam(name = "userId") String userId,
               @Min(value = 0, message = "必须从0页开始")
               @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
               @Min(value = 1, message = "每页必须大于1")
               @Max(value = 300, message = "每页必须小于300")
               @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize);
}

