package com.mouse.web.controller;

import com.mouse.api.commons.req.SaveCartReq;
import com.mouse.api.commons.req.UpdateCartReq;
import com.mouse.api.feign.CartFeign;
import com.mouse.core.base.R;
import com.mouse.core.params.RotorSessionUser;
import com.mouse.web.base.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ; lidongdong
 * @Description 用户购物车服务
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("cart")
public class CartController extends GlobalExceptionHandler {
    @Autowired
    CartFeign cartFeign;

    /**
     * 用户购物车信息
     *
     * @return 用户购物车信息
     */
    @GetMapping("index")
    public R index(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser) {
        return cartFeign.index(sessionUser.getId());
    }

    /**
     * 加入商品到购物车
     * <p>
     * 如果已经存在购物车货品，则增加数量；
     * 否则添加新的购物车货品项。
     *
     * @param param 购物车商品信息， { goodsId: xxx, productId: xxx, number: xxx }
     * @return 加入购物车操作结果
     */
    @PostMapping("add")
    public R add(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                 @RequestBody SaveCartReq param) {
        return cartFeign.add(sessionUser.getId(), param);
    }

    /**
     * 立即购买
     * <p>
     * 和add方法的区别在于：
     * 1. 如果购物车内已经存在购物车货品，前者的逻辑是数量添加，这里的逻辑是数量覆盖
     * 2. 添加成功以后，前者的逻辑是返回当前购物车商品数量，这里的逻辑是返回对应购物车项的ID
     *
     * @param param 购物车商品信息， { goodsId: xxx, productId: xxx, number: xxx }
     * @return 立即购买操作结果
     */
    @PostMapping("fastadd")
    public R fastadd(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                     @RequestBody SaveCartReq param) {
        return cartFeign.fastadd(sessionUser.getId(), param);
    }

    /**
     * 修改购物车商品货品数量
     *
     * @param param 购物车商品信息， { id: xxx, goodsId: xxx, productId: xxx, number: xxx }
     * @return 修改结果
     */
    @PostMapping("update")
    public R update(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestBody UpdateCartReq param) {
        return cartFeign.update(sessionUser.getId(), param);
    }

    /**
     * 购物车商品货品勾选状态
     * <p>
     * 如果原来没有勾选，则设置勾选状态；如果商品已经勾选，则设置非勾选状态。
     *
     * @param isChecked  选中状态
     * @param productIds 产品ID集合
     * @return
     */
    @PostMapping("checked")
    public R checked(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                     @RequestParam(name = "isChecked") Boolean isChecked,
                     @RequestBody List<Integer> productIds) {
        return cartFeign.checked(sessionUser.getId(), isChecked, productIds);
    }

    /**
     * 购物车商品删除
     *
     * @param productIds 购物车商品信息， { productIds: xxx }
     * @return 购物车信息
     * 成功则
     * {
     * errno: 0,
     * errmsg: '成功',
     * data: xxx
     * }
     * 失败则 { errno: XXX, errmsg: XXX }
     */
    @PostMapping("delete")
    public R delete(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestBody List<String> productIds) {
        return cartFeign.delete(sessionUser.getId(), productIds);
    }

    /**
     * 购物车商品货品数量
     * <p>
     * 如果用户没有登录，则返回空数据。
     *
     * @return 购物车商品货品数量
     */
    @GetMapping("count")
    public R count(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser) {
        return cartFeign.count(sessionUser.getId());
    }

    /**
     * 购物车下单
     *
     * @param cartId    购物车商品ID：
     *                  如果购物车商品ID是空，则下单当前用户所有购物车商品；
     *                  如果购物车商品ID非空，则只下单当前购物车商品。
     * @param addressId 收货地址ID：
     *                  如果收货地址ID是空，则查询当前用户的默认地址。
     * @param couponId  优惠券ID：
     *                  如果优惠券ID是空，则自动选择合适的优惠券。
     * @return 购物车操作结果
     */
    @GetMapping("checkout")
    public R checkout(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                      @RequestParam(name = "cartId") Integer cartId,
                      @RequestParam(name = "addressId") Integer addressId,
                      @RequestParam(name = "couponId") Integer couponId,
                      @RequestParam(name = "userCouponId") Integer userCouponId,
                      @RequestParam(name = "grouponRulesId") Integer grouponRulesId) {
        return cartFeign.checkout(sessionUser.getId(), cartId, addressId, couponId, userCouponId, grouponRulesId);
    }
}

