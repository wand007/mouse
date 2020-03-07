package com.mouse.api.hystrix;

import com.mouse.api.feign.BrandFeign;
import com.mouse.core.base.BusinessCode;
import com.mouse.core.base.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ; lidongdong
 * @Description 专题服务 熔断器
 * @Date 2019-11-30
 */
@Slf4j
@Component
public class HystrixBrandFeign implements FallbackFactory<BrandFeign> {
    @Override
    public BrandFeign create(Throwable throwable) {

        log.error("错误信息：", throwable);
        return new BrandFeign() {
            @Override
            public R findPage(Integer pageNum, Integer pageSize, String sort, String order) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R findDetail(Integer id) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }
        };
    }
}
