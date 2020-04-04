package com.mouse.api.hystrix.mall;

import com.mouse.api.feign.mall.CouponFeign;
import com.mouse.core.base.BusinessCode;
import com.mouse.core.base.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 优惠券服务 熔断器
 * @Date 2020-01-12
 */
@Slf4j
@Component
public class HystrixCouponFeign implements FallbackFactory<CouponFeign> {

    @Override
    public CouponFeign create(Throwable throwable) {
        log.error("错误信息：", throwable);
        return new CouponFeign() {

            @Override
            public R findPage(@Min(value = 0, message = "必须从0页开始") Integer pageNum, @Min(value = 1, message = "每页必须大于1") @Max(value = 300, message = "每页必须小于300") Integer pageSize) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R findPersonalPage(String userId, Integer status, @Min(value = 0, message = "必须从0页开始") Integer pageNum, @Min(value = 1, message = "每页必须大于1") @Max(value = 300, message = "每页必须小于300") Integer pageSize) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R selectlist(String userId, Integer cartId, Integer grouponRulesId) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R receive(String userId, Integer couponId) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R exchange(String userId, String code) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }
        };
    }
}
