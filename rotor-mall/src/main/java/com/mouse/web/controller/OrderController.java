package com.mouse.web.controller;

import com.mouse.api.commons.req.SaveCommentReq;
import com.mouse.api.commons.req.SaveOrderReq;
import com.mouse.api.feign.OrderFeign;
import com.mouse.core.base.R;
import com.mouse.core.params.RotorSessionUser;
import com.mouse.web.base.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * @param userId   用户ID
     * @param showType 订单信息
     * @param referer
     * @param pageNum
     * @param pageSize
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("findPage")
    public R findPage(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                      @RequestParam(defaultValue = "0") Integer showType,
                      @RequestParam(name = "referer") Integer referer,
                      @Min(value = 0, message = "必须从0页开始")
                      @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                      @Min(value = 1, message = "每页必须大于1")
                      @Max(value = 300, message = "每页必须小于300")
                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                      @RequestParam(name = "sort", defaultValue = "add_time", required = false) String sort,
                      @RequestParam(name = "order", defaultValue = "desc", required = false) String order) {
        return orderFeign.findPage(sessionUser.getId(), showType, referer, pageNum, pageSize, sort, order);
    }

    /**
     * 订单详情
     *
     * @param userId  用户ID
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
     * @param userId 用户ID
     * @param param  订单信息，{ cartId：xxx, addressId: xxx, couponId: xxx, message: xxx, grouponRulesId: xxx,  grouponLinkId: xxx}
     * @return 提交订单操作结果
     */
    @PostMapping("submit")
    public R submit(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestBody SaveOrderReq param) {
        return orderFeign.submit(sessionUser.getId(), param);
    }

    /**
     * 取消订单
     *
     * @param userId  用户ID
     * @param orderId 订单信息，{ orderId：xxx }
     * @return 取消订单操作结果
     */
    @PostMapping("cancel")
    public R cancel(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestParam("orderId") String orderId) {
        return orderFeign.cancel(sessionUser.getId(), orderId);
    }

    /**
     * 付款订单的预支付会话标识
     *
     * @param userId  用户ID
     * @param orderId 订单信息，{ orderId：xxx }
     * @return 支付订单ID
     */
    @PostMapping("prepay")
    public R prepay(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestParam("orderId") String orderId) {
        return orderFeign.prepay(sessionUser.getId(), orderId);
    }

    /**
     * 微信H5支付
     *
     * @param userId
     * @param orderId
     * @param request
     * @return
     */
    @PostMapping("h5pay")
    public R h5pay(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                   @RequestParam("orderId") String orderId) {
        return orderFeign.h5pay(sessionUser.getId(), orderId);
    }

    /**
     * 微信付款成功或失败回调接口
     * <p>
     * TODO
     * 注意，这里pay-notify是示例地址，建议开发者应该设立一个隐蔽的回调地址
     *
     * @param request  请求内容
     * @param response 响应内容
     * @return 操作结果
     */
    @PostMapping("pay-notify")
    public R payNotify(HttpServletRequest request, HttpServletResponse response) {
        return R.success();
    }

    /**
     * 订单申请退款
     *
     * @param userId  用户ID
     * @param orderId 订单信息，{ orderId：xxx }
     * @return 订单退款操作结果
     */
    @PostMapping("refund")
    public R refund(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestParam("orderId") String orderId) {
        return orderFeign.refund(sessionUser.getId(), orderId);
    }

    /**
     * 确认收货
     *
     * @param userId  用户ID
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
     * @param userId  用户ID
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
     * @param userId  用户ID
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
     * @param userId 用户ID
     * @param param  订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @PostMapping("comment")
    public R comment(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                     @RequestBody SaveCommentReq param) {
        return orderFeign.comment(sessionUser.getId(), param);
    }
}
