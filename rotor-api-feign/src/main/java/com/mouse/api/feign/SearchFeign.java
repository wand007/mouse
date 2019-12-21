package com.mouse.api.feign;

import com.mouse.api.hystrix.HystrixSearchFeign;
import com.mouse.core.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 商品搜索服务 feign
 * @Date 2019-12-15
 */
@FeignClient(name = "http://ROTOR-API",
        path = "rotor-api/search",
        fallbackFactory = HystrixSearchFeign.class)
public interface SearchFeign {

    /**
     * 搜索页面信息
     * <p>
     * 如果用户已登录，则给出用户历史搜索记录；
     * 如果没有登录，则给出空历史搜索记录。
     *
     * @param userId 用户ID，可选
     * @return 搜索页面信息
     */
    @GetMapping("index")
    R index(@RequestParam(name = "userId") Integer userId);

    /**
     * 关键字提醒
     * <p>
     * 当用户输入关键字一部分时，可以推荐系统中合适的关键字。
     *
     * @param keyword 关键字
     * @return 合适的关键字
     */
    @GetMapping("helper")
    R helper(@RequestParam(name = "keyword") String keyword,
             @Min(value = 0, message = "必须从0页开始")
             @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
             @Min(value = 1, message = "每页必须大于1")
             @Max(value = 300, message = "每页必须小于300")
             @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize);

    /**
     * 清除用户搜索历史
     *
     * @param userId 用户ID
     * @return 清理是否成功
     */
    @PostMapping("clearhistory")
    R clearhistory(@RequestParam(name = "userId") Integer userId);
}

