package com.mouse.api.feign.mall;

import com.mouse.api.hystrix.mall.HystrixIssueFeign;
import com.mouse.core.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 用户服务
 * @Date 2019-12-15
 */
@FeignClient(name = "http://ROTOR-API",
        path = "rotor-api/issue",
        fallbackFactory = HystrixIssueFeign.class)
public interface IssueFeign {
    /**
     * 帮助中心
     *
     * @param pageNum
     * @param pageSize
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("findPage")
    R findPage(@RequestParam(name = "question", required = false) String question,
               @Min(value = 0, message = "必须从0页开始")
               @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
               @Min(value = 1, message = "每页必须大于1")
               @Max(value = 300, message = "每页必须小于300")
               @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
               @RequestParam(name = "sort", defaultValue = "add_time", required = false) String sort,
               @RequestParam(name = "order", defaultValue = "desc", required = false) String order);

}

