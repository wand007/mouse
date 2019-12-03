package com.mouse.api.feign;

import com.mouse.api.hystrix.HystrixResourcesFeign;
import com.mouse.core.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 资源Api feign
 * @Date 2019-11-30
 */
@FeignClient(name = "http://ROTOR-API",
        path = "rotor-api/resources",
        fallbackFactory = HystrixResourcesFeign.class)
public interface ResourcesFeign {

    /**
     * 分页查询商品列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @GetMapping(value = "findPage")
    public R findPage(
            @Min(value = 0, message = "必须从0页开始")
            @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
            @Min(value = 1, message = "每页必须大于1")
            @Max(value = 300, message = "每页必须小于300")
            @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize);
}
