package com.mouse.web.controller;

import com.mouse.api.commons.enums.RefererEnum;
import com.mouse.api.commons.req.SaveCommentReq;
import com.mouse.api.commons.req.SaveOrderReq;
import com.mouse.api.feign.mall.OrderFeign;
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
 * @Description 订单服务
 * @Date 2020-01-28
 */

@Slf4j
@Validated
@RestController
@RequestMapping("order")
public class OrderController extends GlobalExceptionHandler {
    @Autowired
    OrderFeign orderFeign;

    /**
     * 订单列表
     *
     * @param showType 订单信息
     * @param pageNum
     * @param pageSize
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("findPage")
    public R findPage(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                      @RequestParam(defaultValue = "0") Integer showType,
                      @Min(value = 0, message = "必须从0页开始")
                      @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                      @Min(value = 1, message = "每页必须大于1")
                      @Max(value = 300, message = "每页必须小于300")
                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                      @RequestParam(name = "sort", defaultValue = "add_time", required = false) String sort,
                      @RequestParam(name = "order", defaultValue = "desc", required = false) String order) {
        return orderFeign.findPage(sessionUser.getId(), showType, RefererEnum.WX, pageNum, pageSize, sort, order);
    }

    /**
     * 订单详情
     *
     * @param orderId 订单ID
     * @return 订单详情
     */
    @GetMapping("detail")
    public R detail(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestParam("orderId") String orderId) {
        return orderFeign.detail(sessionUser.getId(), orderId);
    }

    /**
     * 提交订单
     *
     * @param param 订单信息，{ cartId：xxx, addressId: xxx, couponId: xxx, message: xxx, grouponRulesId: xxx,  grouponLinkId: xxx}
     * @return 提交订单操作结果
     */
    @PostMapping("submit")
    public R submit(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestBody SaveOrderReq param) {
        param.setUserId(sessionUser.getId());
        return orderFeign.submit(param);
    }

    /**
     * 取消订单
     *
     * @param orderId 订单信息，{ orderId：xxx }
     * @return 取消订单操作结果
     */
    @PostMapping("cancel")
    public R cancel(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestParam("orderId") String orderId) {
        return orderFeign.cancel(sessionUser.getId(), orderId);
    }

    /**
     * 确认收货
     *
     * @param orderId 订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @PostMapping("confirm")
    public R confirm(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                     @RequestParam("orderId") String orderId) {
        return orderFeign.confirm(sessionUser.getId(), orderId);
    }

    /**
     * 删除订单
     *
     * @param orderId 订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @PostMapping("delete")
    public R delete(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestParam("orderId") String orderId) {
        return orderFeign.delete(sessionUser.getId(), orderId);
    }

    /**
     * 待评价订单商品信息
     *
     * @param orderId 订单ID
     * @param goodsId 商品ID
     * @return 待评价订单商品信息
     */
    @GetMapping("goods")
    public R goods(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                   @RequestParam("orderId") String orderId,
                   @RequestParam("goodsId") Integer goodsId) {
        return orderFeign.goods(sessionUser.getId(), orderId, goodsId);
    }

    /**
     * 评价订单商品
     *
     * @param param 订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @PostMapping("comment")
    public R comment(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                     @RequestBody SaveCommentReq param) {
        return orderFeign.comment(sessionUser.getId(), param);
    }
}
