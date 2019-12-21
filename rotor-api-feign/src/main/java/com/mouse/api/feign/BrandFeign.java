package com.mouse.api.feign;

import com.mouse.api.hystrix.HystrixBrandFeign;
import com.mouse.core.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author ; lidongdong
 * @Description 专题服务feign
 * @Date 2019-12-15
 */
@FeignClient(name = "http://ROTOR-API",
        path = "rotor-api/brand",
        fallbackFactory = HystrixBrandFeign.class)
public interface BrandFeign {
    /**
     * 品牌列表
     *
     * @param pageNum
     * @param pageSize
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("findPage")
    R findPage(@Min(value = 0, message = "必须从0页开始")
               @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
               @Min(value = 1, message = "每页必须大于1")
               @Max(value = 300, message = "每页必须小于300")
               @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
               @RequestParam(defaultValue = "add_time", required = false) String sort,
               @RequestParam(defaultValue = "desc", required = false) String order);

    /**
     * 品牌详情
     *
     * @param id 品牌ID
     * @return 品牌详情
     */
    @GetMapping("detail")
    R detail(@NotNull Integer id);
}

