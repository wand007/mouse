package com.mouse.api.feign;

import com.mouse.api.commons.req.CartCheckedReq;
import com.mouse.api.commons.req.SaveCartReq;
import com.mouse.api.commons.req.UpdateCartReq;
import com.mouse.api.hystrix.HystrixCartFeign;
import com.mouse.core.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author ; lidongdong
 * @Description 用户购物车服务 feign
 * @Date 2019-12-15
 */
@FeignClient(name = "http://ROTOR-API",
        path = "rotor-api/cart",
        fallbackFactory = HystrixCartFeign.class)
public interface CartFeign {
    /**
     * 用户购物车信息
     *
     * @param userId 用户ID
     * @return 用户购物车信息
     */
    @GetMapping("index")
    R index(@RequestParam(name = "userId") String userId);

    /**
     * 加入商品到购物车
     * <p>
     * 如果已经存在购物车货品，则增加数量；
     * 否则添加新的购物车货品项。
     *
     * @param userId 用户ID
     * @param param  购物车商品信息， { goodsId: xxx, productId: xxx, number: xxx }
     * @return 加入购物车操作结果
     */
    @PostMapping("add")
    R add(@RequestParam(name = "userId") String userId,
          @RequestBody SaveCartReq param);

    /**
     * 立即购买
     * <p>
     * 和add方法的区别在于：
     * 1. 如果购物车内已经存在购物车货品，前者的逻辑是数量添加，这里的逻辑是数量覆盖
     * 2. 添加成功以后，前者的逻辑是返回当前购物车商品数量，这里的逻辑是返回对应购物车项的ID
     *
     * @param userId 用户ID
     * @param param  购物车商品信息， { goodsId: xxx, productId: xxx, number: xxx }
     * @return 立即购买操作结果
     */
    @PostMapping("fastadd")
    R fastadd(@RequestParam(name = "userId") String userId,
              @RequestBody SaveCartReq param);

    /**
     * 修改购物车商品货品数量
     *
     * @param userId 用户ID
     * @param param  购物车商品信息， { id: xxx, goodsId: xxx, productId: xxx, number: xxx }
     * @return 修改结果
     */
    @PostMapping("update")
    R update(@RequestParam(name = "userId") String userId,
             @RequestBody UpdateCartReq param);

    /**
     * 购物车商品货品勾选状态
     * <p>
     * 如果原来没有勾选，则设置勾选状态；如果商品已经勾选，则设置非勾选状态。
     *
     * @param userId 用户ID
     * @param param
     * @return
     */
    @PostMapping("checked")
    R checked(@RequestParam(name = "userId") String userId,
              @RequestBody CartCheckedReq param);

    /**
     * 购物车商品删除
     *
     * @param userId     用户ID
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
    R delete(@RequestParam(name = "userId") String userId,
             @RequestBody List<String> productIds);

    /**
     * 购物车商品货品数量
     * <p>
     * 如果用户没有登录，则返回空数据。
     *
     * @param userId 用户ID
     * @return 购物车商品货品数量
     */
    @GetMapping("count")
    R count(@RequestParam(name = "userId") String userId);

    /**
     * 购物车下单
     *
     * @param userId    用户ID
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
    R checkout(@RequestParam(name = "userId") String userId,
               @RequestParam(name = "cartId") Integer cartId,
               @RequestParam(name = "addressId") Integer addressId,
               @RequestParam(name = "couponId") Integer couponId,
               @RequestParam(name = "userCouponId") Integer userCouponId,
               @RequestParam(name = "grouponRulesId") Integer grouponRulesId);
}

