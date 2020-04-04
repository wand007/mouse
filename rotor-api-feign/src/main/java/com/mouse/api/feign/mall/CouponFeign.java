package com.mouse.api.feign.mall;

import com.mouse.api.hystrix.mall.HystrixCouponFeign;
import com.mouse.core.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 优惠券服务 feign
 * @Date 2020-01-12
 */
@FeignClient(name = "http://ROTOR-API",
        path = "rotor-api/coupon",
        fallbackFactory = HystrixCouponFeign.class)
public interface CouponFeign {

    /**
     * 优惠券列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("findPage")
    R findPage(@Min(value = 0, message = "必须从0页开始")
               @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
               @Min(value = 1, message = "每页必须大于1")
               @Max(value = 300, message = "每页必须小于300")
               @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize);

    /**
     * 个人优惠券列表
     *
     * @param userId
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("personal/findPage")
    R findPersonalPage(@RequestParam(name = "userId") String userId,
                       @RequestParam(name = "status") Integer status,
                       @Min(value = 0, message = "必须从0页开始")
                       @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                       @Min(value = 1, message = "每页必须大于1")
                       @Max(value = 300, message = "每页必须小于300")
                       @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize);


    /**
     * 当前购物车下单商品订单可用优惠券
     *
     * @param userId
     * @param cartId
     * @param grouponRulesId
     * @return
     */
    @GetMapping("selectlist")
    R selectlist(@RequestParam(name = "userId") String userId,
                 @RequestParam(name = "cartId") Integer cartId,
                 @RequestParam(name = "grouponRulesId") Integer grouponRulesId);

    /**
     * 优惠券领取
     *
     * @param userId   用户ID
     * @param couponId 优惠券ID
     * @return 操作结果
     */
    @PostMapping("receive")
    R receive(@RequestParam(name = "userId") String userId,
              @RequestParam(name = "couponId") Integer couponId);

    /**
     * 优惠券兑换
     *
     * @param userId 用户ID
     * @param code   优惠券code
     * @return 操作结果
     */
    @PostMapping("exchange")
    R exchange(@RequestParam(name = "userId") String userId,
               @RequestParam(name = "code") String code);
}
