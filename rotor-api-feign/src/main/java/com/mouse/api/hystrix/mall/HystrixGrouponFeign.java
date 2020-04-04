package com.mouse.api.hystrix.mall;

import com.mouse.api.feign.mall.GrouponFeign;
import com.mouse.core.base.BusinessCode;
import com.mouse.core.base.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 团购服务 熔断器
 * @Date 2019-11-30
 */
@Slf4j
@Component
public class HystrixGrouponFeign implements FallbackFactory<GrouponFeign> {
    @Override
    public GrouponFeign create(Throwable throwable) {

        log.error("错误信息：", throwable);
        return new GrouponFeign() {
            @Override
            public R findPage(@Min(value = 0, message = "必须从0页开始") Integer pageNum, @Min(value = 1, message = "每页必须大于1") @Max(value = 300, message = "每页必须小于300") Integer pageSize, String sort, String order) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R detail(String userId, Integer grouponId) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R join(Integer grouponId) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R my(String userId, Integer showType) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }
        };
    }
}
