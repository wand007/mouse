package com.mouse.api.hystrix;

import com.mouse.api.feign.GrouponFeign;
import com.mouse.api.feign.HomeFeign;
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
                return R.error();
            }

            @Override
            public R detail(Integer userId, Integer grouponId) {
                return R.error();
            }

            @Override
            public R join(Integer grouponId) {
                return R.error();
            }

            @Override
            public R my(Integer userId, Integer showType) {
                return R.error();
            }
        };
    }
}
