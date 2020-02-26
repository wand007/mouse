package com.mouse.web.controller;

import com.mouse.api.feign.CouponFeign;
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
 * @Description 优惠券服务
 * @Date 2020-01-12
 */

@Slf4j
@Validated
@RestController
@RequestMapping("coupon")
public class CouponController extends GlobalExceptionHandler {
    @Autowired
    CouponFeign couponFeign;

    /**
     * 优惠券列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("findPage")
    public R findPage(@Min(value = 0, message = "必须从0页开始")
                      @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                      @Min(value = 1, message = "每页必须大于1")
                      @Max(value = 300, message = "每页必须小于300")
                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        return couponFeign.findPage(pageNum, pageSize);
    }

    /**
     * 个人优惠券列表
     *
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("personal/findPage")
    public R findPersonalPage(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                              @RequestParam(name = "status") Integer status,
                              @Min(value = 0, message = "必须从0页开始")
                              @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                              @Min(value = 1, message = "每页必须大于1")
                              @Max(value = 300, message = "每页必须小于300")
                              @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        return couponFeign.findPersonalPage(sessionUser.getId(), status, pageNum, pageSize);
    }


    /**
     * 当前购物车下单商品订单可用优惠券
     *
     * @param cartId
     * @param grouponRulesId
     * @return
     */
    @GetMapping("availableList")
    public R availableList(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                           @RequestParam(name = "cartId") Integer cartId,
                           @RequestParam(name = "grouponRulesId") Integer grouponRulesId) {
        return couponFeign.availableList(sessionUser.getId(), cartId, grouponRulesId);
    }

    /**
     * 优惠券领取
     *
     * @param couponId 优惠券ID
     * @return 操作结果
     */
    @PostMapping("receive")
    public R receive(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                     @RequestParam(name = "couponId") Integer couponId) {
        return couponFeign.receive(sessionUser.getId(), couponId);
    }

    /**
     * 优惠券兑换
     *
     * @param code 优惠券code
     * @return 操作结果
     */
    @PostMapping("exchange")
    public R exchange(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                      @RequestParam(name = "code") String code) {
        return couponFeign.exchange(sessionUser.getId(), code);
    }
}
