package com.mouse.api.hystrix.mall;

import com.mouse.api.feign.mall.WxCommFeign;
import com.mouse.core.base.BusinessCode;
import com.mouse.core.base.R;
import com.mouse.core.enums.ReceiptChannelEnum;
import com.mouse.core.wx.param.WxUserInfo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ; lidongdong
 * @Description 微信通用服务 熔断器
 * @Date 2019-11-30
 */
@Slf4j
@Component
public class HystrixWxCommFeign implements FallbackFactory<WxCommFeign> {
    @Override
    public WxCommFeign create(Throwable throwable) {

        log.error("错误信息：", throwable);
        return new WxCommFeign() {

            @Override
            public R payCallback(ReceiptChannelEnum payType, String notifyParam) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R updateWxH5AuthCall(String userId, WxUserInfo wxUserInfo) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }
        };

    }
}
