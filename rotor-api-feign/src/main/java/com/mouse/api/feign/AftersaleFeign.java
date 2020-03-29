package com.mouse.api.feign;

import com.mouse.api.commons.req.SaveAftersaleReq;
import com.mouse.api.hystrix.HystrixAftersaleFeign;
import com.mouse.core.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


/**
 * @author ; lidongdong
 * @Description 售后服务 feign
 * @Date 2019-12-15
 */
@FeignClient(name = "http://ROTOR-API",
        path = "rotor-api/aftersale",
        fallbackFactory = HystrixAftersaleFeign.class)
public interface AftersaleFeign {

    /**
     * 售后列表
     *
     * @param userId   用户ID
     * @param status   状态类型，如果是空则是全部
     * @param pageNum  分页页数
     * @param pageSize 分页大小
     * @return 售后列表
     */
    @GetMapping("findPage")
    R findPage(@RequestParam(name = "userId") String userId,
               @RequestParam(name = "status") Integer status, @Min(value = 0, message = "必须从0页开始")
               @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
               @Min(value = 1, message = "每页必须大于1")
               @Max(value = 300, message = "每页必须小于300")
               @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize);

    /**
     * 售后详情
     *
     * @param orderId 订单ID
     * @return 售后详情
     */
    @GetMapping("detail")
    R detail(@RequestParam(name = "userId") String userId, @RequestParam(name = "orderId") Integer orderId);

    /**
     * 申请售后
     *
     * @param userId    用户ID
     * @param aftersale 用户售后信息
     * @return 操作结果
     */
    @PostMapping("submit")
    R submit(@RequestParam(name = "userId") String userId, @RequestBody SaveAftersaleReq aftersale);

    /**
     * 取消售后
     * <p>
     * 如果管理员还没有审核，用户可以取消自己的售后申请
     *
     * @param userId 用户ID
     * @param id     售后记录ID
     * @return 操作结果
     */
    @PostMapping("cancel")
    R cancel(@RequestParam(name = "userId") String userId,
             @RequestParam(name = "id") Integer id);
}