package com.mouse.api.hystrix;

import com.mouse.api.feign.CollectFeign;
import com.mouse.core.base.BusinessCode;
import com.mouse.core.base.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 用户收藏服务 熔断器
 * @Date 2020-01-12
 */
@Slf4j
@Component
public class HystrixCollectFeign implements FallbackFactory<CollectFeign> {
    @Override
    public CollectFeign create(Throwable throwable) {

        log.error("错误信息：", throwable);
        return new CollectFeign() {
            @Override
            public R findPage(String userId, Byte type, @Min(value = 0, message = "必须从0页开始") Integer pageNum, @Min(value = 1, message = "每页必须大于1") @Max(value = 300, message = "每页必须小于300") Integer pageSize) {
               return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R addOrDelete(String userId, Integer type, Integer valueId) {
               return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }
        };
    }
}
