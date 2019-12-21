package com.mouse.api.hystrix;

import com.mouse.api.feign.CatalogFeign;
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
            public Object getFirstCategory() {
                return R.error();
            }

            @Override
            public Object getSecondCategory(@NotNull Integer id) {
                return R.error();
            }

            @Override
            public Object index(Integer id) {
                return R.error();
            }

            @Override
            public Object queryAll() {
                return R.error();
            }

            @Override
            public Object current(@NotNull Integer id) {
                return R.error();
            }
        };
    }
}
