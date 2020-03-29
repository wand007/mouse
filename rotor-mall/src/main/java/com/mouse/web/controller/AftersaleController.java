package com.mouse.web.controller;

import com.mouse.api.commons.req.SaveAftersaleReq;
import com.mouse.api.feign.AftersaleFeign;
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
 * @Description 售后服务
 * 目前只支持订单整体售后，不支持订单商品单个售后
 * 一个订单只能有一个售后记录
 * @Date 2019-11-26
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/wx/aftersale")
public class AftersaleController extends GlobalExceptionHandler {

    @Autowired
    private AftersaleFeign aftersaleFeign;

    /**
     * 售后列表
     *
     * @param sessionUser 用户ID
     * @param status      状态类型，如果是空则是全部
     * @param pageNum     分页页数
     * @param pageSize    分页大小
     * @return 售后列表
     */
    @GetMapping("findPage")
    R findPage(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
               @RequestParam(name = "status") Integer status,
               @Min(value = 0, message = "必须从0页开始")
               @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
               @Min(value = 1, message = "每页必须大于1")
               @Max(value = 300, message = "每页必须小于300")
               @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        return aftersaleFeign.findPage(sessionUser.getId(), status, pageNum, pageSize);
    }

    /**
     * 售后详情
     *
     * @param orderId 订单ID
     * @return 售后详情
     */
    @GetMapping("detail")
    public R detail(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestParam(name = "orderId") Integer orderId) {
        return aftersaleFeign.detail(sessionUser.getId(), orderId);
    }

    /**
     * 申请售后
     *
     * @param sessionUser 用户ID
     * @param aftersale   用户售后信息
     * @return 操作结果
     */
    @PostMapping("submit")
    public R submit(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestBody SaveAftersaleReq aftersale) {
        return aftersaleFeign.submit(sessionUser.getId(), aftersale);
    }

    /**
     * 取消售后
     * <p>
     * 如果管理员还没有审核，用户可以取消自己的售后申请
     *
     * @param sessionUser 用户ID
     * @param id          用户售后信息
     * @return 操作结果
     */
    @PostMapping("cancel")
    public R cancel(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestParam(name = "id") Integer id) {
        return aftersaleFeign.cancel(sessionUser.getId(), id);
    }
}