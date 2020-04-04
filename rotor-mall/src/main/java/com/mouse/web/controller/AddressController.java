package com.mouse.web.controller;

import com.mouse.api.commons.req.SaveAddressReq;
import com.mouse.api.feign.mall.AddressFeign;
import com.mouse.core.base.R;
import com.mouse.core.params.RotorSessionUser;
import com.mouse.web.base.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 用户收货地址服务
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("address")
public class AddressController extends GlobalExceptionHandler {
    @Autowired
    AddressFeign addressFeign;

    /**
     * 用户收货地址列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("findPage")
    public R findPage(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                      @Min(value = 0, message = "必须从0页开始")
                      @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                      @Min(value = 1, message = "每页必须大于1")
                      @Max(value = 300, message = "每页必须小于300")
                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        return addressFeign.findPage(sessionUser.getId(), pageNum, pageSize);
    }


    /**
     * 收货地址详情
     *
     * @param id 收货地址ID
     * @return 收货地址详情
     */
    @GetMapping("detail")
    public R detail(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestParam(name = "id") Integer id) {
        return addressFeign.detail(sessionUser.getId(), id);
    }


    /**
     * 添加收货地址
     *
     * @param param 用户收货地址
     * @return 更新操作结果
     */
    @PostMapping("save")
    public R save(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                  @RequestBody SaveAddressReq param) {
        param.setUserId(sessionUser.getId());
        return addressFeign.save(param);
    }

    /**
     * 更新收货地址
     *
     * @param param 用户收货地址
     * @return 添加操作结果
     */
    @PostMapping("update")
    public R update(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestBody SaveAddressReq param) {
        param.setUserId(sessionUser.getId());
        return addressFeign.save(param);
    }

    /**
     * 删除收货地址
     *
     * @param id 收货地址ID
     * @return 删除操作结果
     */
    @PostMapping("delete")
    public R delete(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestParam(name = "id") Integer id) {
        return addressFeign.delete(sessionUser.getId(), id);
    }
}

