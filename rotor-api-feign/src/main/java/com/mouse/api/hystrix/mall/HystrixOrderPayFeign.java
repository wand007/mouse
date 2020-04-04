package com.mouse.api.hystrix.mall;

import com.mouse.api.feign.mall.OrderPayFeign;
import com.mouse.core.base.BusinessCode;
import com.mouse.core.base.R;
import com.mouse.core.enums.ReceiptChannelEnum;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ; lidongdong
 * @Description 订单支付服务 熔断器
 * @Date 2019-11-30
 */
@Slf4j
@Component
public class HystrixOrderPayFeign implements FallbackFactory<OrderPayFeign> {
    @Override
    public OrderPayFeign create(Throwable throwable) {

        log.error("错误信息：", throwable);
        return new OrderPayFeign() {

            @Override
            public R prepay(String userId, String orderId) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R payOrder(String orderId, String userId, ReceiptChannelEnum receiptChannel) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R refund(String userId, String orderId) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }
        };
    }
}
