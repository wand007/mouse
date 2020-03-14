package com.mouse.api.hystrix;

import com.mouse.api.commons.req.CartCheckedReq;
import com.mouse.api.commons.req.SaveCartReq;
import com.mouse.api.commons.req.UpdateCartReq;
import com.mouse.api.feign.CartFeign;
import com.mouse.core.base.BusinessCode;
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
            public R index(String userId) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R add(String userId, SaveCartReq cart) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R fastadd(String userId, SaveCartReq param) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R update(String userId, UpdateCartReq param) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R checked(String userId, CartCheckedReq param) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R delete(String userId, List<String> productIds) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R count(String userId) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R checkout(String userId, Integer cartId, Integer addressId, Integer couponId, Integer userCouponId, Integer grouponRulesId) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }
        };
    }
}
