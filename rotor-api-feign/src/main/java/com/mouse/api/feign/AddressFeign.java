package com.mouse.api.feign;

import com.mouse.api.commons.req.SaveAddressReq;
import com.mouse.api.hystrix.HystrixAddressFeign;
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
 * @Description 用户收货地址服务 feign
 * @Date 2019-12-15
 */
@FeignClient(name = "http://ROTOR-API",
        path = "rotor-api/address",
        fallbackFactory = HystrixAddressFeign.class)
public interface AddressFeign {
    /**
     * 用户收货地址列表
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("findPage")
    R findPage(@RequestParam(name = "userId") String userId,
               @Min(value = 0, message = "必须从0页开始")
               @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
               @Min(value = 1, message = "每页必须大于1")
               @Max(value = 300, message = "每页必须小于300")
               @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize);


    /**
     * 收货地址详情
     *
     * @param userId 用户ID
     * @param id     收货地址ID
     * @return 收货地址详情
     */
    @GetMapping("detail")
    R detail(@RequestParam(name = "userId") String userId,
             @RequestParam(name = "id") Integer id);


    /**
     * 添加收货地址
     *
     * @param param 用户收货地址
     * @return 更新操作结果
     */
    @PostMapping("save")
    R save(@RequestBody SaveAddressReq param);

    /**
     * 更新收货地址
     *
     * @param param 用户收货地址
     * @return 添加操作结果
     */
    @PostMapping("update")
    R update(@RequestBody SaveAddressReq param);

    /**
     * 删除收货地址
     *
     * @param userId 用户ID
     * @param id     收货地址ID
     * @return 删除操作结果
     */
    @PostMapping("delete")
    R delete(@RequestParam(name = "userId") String userId,
             @RequestParam(name = "id") Integer id);
}

