package com.mouse.api.hystrix;

import com.mouse.api.feign.FootprintFeign;
import com.mouse.core.base.BusinessCode;
import com.mouse.core.base.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 用户访问足迹服务 熔断器
 * @Date 2019-11-30
 */
@Slf4j
@Component
public class HystrixFootprintFeign implements FallbackFactory<FootprintFeign> {
    @Override
    public FootprintFeign create(Throwable throwable) {

        log.error("错误信息：", throwable);
        return new FootprintFeign() {
            @Override
            public R delete(String userId, String id) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R findPage(String userId, @Min(value = 0, message = "必须从0页开始") Integer pageNum, @Min(value = 1, message = "每页必须大于1") @Max(value = 300, message = "每页必须小于300") Integer pageSize) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }
        };
    }
}
