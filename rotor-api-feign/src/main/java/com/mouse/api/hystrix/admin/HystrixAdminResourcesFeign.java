package com.mouse.api.hystrix.admin;

import com.mouse.api.feign.admin.AdminResourcesFeign;
import com.mouse.core.base.BusinessCode;
import com.mouse.core.base.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 资源Api 熔断器
 * @Date 2019-11-30
 */
@Slf4j
@Component
public class HystrixAdminResourcesFeign implements FallbackFactory<AdminResourcesFeign> {
    @Override
    public AdminResourcesFeign create(Throwable throwable) {

        log.error("错误信息：", throwable);
        return new AdminResourcesFeign() {
            @Override
            public R findPage(Integer categoryId, Integer brandId, String keyword, Boolean isNew, Boolean isHot, String userId, Integer referer, @Min(value = 0, message = "必须从0页开始") Integer pageNum, @Min(value = 1, message = "每页必须大于1") @Max(value = 300, message = "每页必须小于300") Integer pageSize, String sort, String order) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R detail(String userId, Integer id) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R category(Integer id) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R related(Integer id) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R count() {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }
        };
    }
}
