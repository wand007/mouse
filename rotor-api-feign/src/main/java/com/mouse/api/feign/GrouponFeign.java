package com.mouse.api.feign;

import com.mouse.api.hystrix.HystrixGrouponFeign;
import com.mouse.core.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 团购服务
 * @Date 2019-12-15
 */
@FeignClient(name = "http://ROTOR-API",
        path = "rotor-api/groupon",
        fallbackFactory = HystrixGrouponFeign.class)
public interface GrouponFeign {
    /**
     * 团购规则列表
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
     * 团购活动详情
     *
     * @param userId    用户ID
     * @param grouponId 团购活动ID
     * @return 团购活动详情
     */
    @GetMapping("detail")
    R detail(@RequestParam(name = "userId") Integer userId,
             @RequestParam(name = "grouponId") Integer grouponId);

    /**
     * 参加团购
     *
     * @param grouponId 团购活动ID
     * @return 操作结果
     */
    @GetMapping("join")
    R join(@RequestParam(name = "grouponId") Integer grouponId);

    /**
     * 用户开团或入团情况
     *
     * @param userId   用户ID
     * @param showType 显示类型，如果是0，则是当前用户开的团购；否则，则是当前用户参加的团购
     * @return 用户开团或入团情况
     */
    @GetMapping("my")
    R my(@RequestParam(name = "userId") Integer userId,
         @RequestParam(name = "showType") Integer showType);

}

