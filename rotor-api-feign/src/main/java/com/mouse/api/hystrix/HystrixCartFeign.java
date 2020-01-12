package com.mouse.api.hystrix;

import com.mouse.api.commons.req.SaveCartReq;
import com.mouse.api.commons.req.UpdateCartReq;
import com.mouse.api.feign.CartFeign;
import com.mouse.core.base.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ; lidongdong
 * @Description 用户购物车服务 熔断器
 * @Date 2019-11-30
 */
@Slf4j
@Component
public class HystrixCartFeign implements FallbackFactory<CartFeign> {
    @Override
    public CartFeign create(Throwable throwable) {

        log.error("错误信息：", throwable);
        return new CartFeign() {
            @Override
            public R index(Integer userId) {
                return R.error();
            }

            @Override
            public R add(Integer userId, SaveCartReq cart) {
                return R.error();
            }

            @Override
            public R fastadd(Integer userId, SaveCartReq param) {
                return R.error();
            }

            @Override
            public R update(Integer userId, UpdateCartReq param) {
                return R.error();
            }

            @Override
            public R checked(Integer userId, Boolean isChecked, List<Integer> productIds) {
                return R.error();
            }

            @Override
            public R delete(Integer userId, List<String> productIds) {
                return R.error();
            }

            @Override
            public R count(Integer userId) {
                return R.error();
            }

            @Override
            public R checkout(Integer userId, Integer cartId, Integer addressId, Integer couponId, Integer userCouponId, Integer grouponRulesId) {
                return R.error();
            }
        };
    }
}
