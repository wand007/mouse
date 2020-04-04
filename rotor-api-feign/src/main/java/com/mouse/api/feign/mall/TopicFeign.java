package com.mouse.api.feign.mall;

import com.mouse.api.hystrix.mall.HystrixTopicFeign;
import com.mouse.core.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 专题服务 feign
 * @Date 2019-12-15
 */
@FeignClient(name = "http://ROTOR-API",
        path = "rotor-api/topic",
        fallbackFactory = HystrixTopicFeign.class)
public interface TopicFeign {

    /**
     * 专题列表
     *
     * @param pageNum
     * @param pageSize
     * @param sort
     * @param order
     * @return 专题列表
     */
    @GetMapping("findPage")
    R findPage(@Min(value = 0, message = "必须从0页开始")
               @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
               @Min(value = 1, message = "每页必须大于1")
               @Max(value = 300, message = "每页必须小于300")
               @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
               @RequestParam(name = "sort", defaultValue = "add_time", required = false) String sort,
               @RequestParam(name = "order", defaultValue = "desc", required = false) String order);

    /**
     * 专题详情
     *
     * @param id 专题ID
     * @return 专题详情
     */
    @GetMapping("detail")
    R detail(@RequestParam(name = "id") Integer id);

    /**
     * 相关专题
     *
     * @param id 专题ID
     * @return 相关专题
     */
    @GetMapping("related")
    R related(@RequestParam(name = "id") Integer id);
}

