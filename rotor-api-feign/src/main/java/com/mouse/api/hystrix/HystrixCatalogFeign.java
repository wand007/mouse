package com.mouse.api.hystrix;

import com.mouse.api.feign.CatalogFeign;
import com.mouse.core.base.BusinessCode;
import com.mouse.core.base.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author ; lidongdong
 * @Description 类目服务服务 熔断器
 * @Date 2019-11-30
 */
@Slf4j
@Component
public class HystrixCatalogFeign implements FallbackFactory<CatalogFeign> {
    @Override
    public CatalogFeign create(Throwable throwable) {

        log.error("错误信息：", throwable);
        return new CatalogFeign() {
            @Override
            public R findFirstCategory() {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R findSecondCategory(@NotNull Integer id) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R index(Integer id) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R findAll() {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R current(@NotNull Integer id) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }
        };
    }
}
