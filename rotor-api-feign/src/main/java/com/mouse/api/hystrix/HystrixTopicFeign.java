package com.mouse.api.hystrix;

import com.mouse.api.feign.TopicFeign;
import com.mouse.core.base.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 专题服务 熔断器
 * @Date 2019-11-30
 */
@Slf4j
@Component
public class HystrixTopicFeign implements FallbackFactory<TopicFeign> {
    @Override
    public TopicFeign create(Throwable throwable) {

        log.error("错误信息：", throwable);
        return new TopicFeign() {
            @Override
            public R findPage(@Min(value = 0, message = "必须从0页开始") Integer pageNum, @Min(value = 1, message = "每页必须大于1") @Max(value = 300, message = "每页必须小于300") Integer pageSize, String sort, String order) {
                return R.error();
            }

            @Override
            public R detail(Integer id) {
                return R.error();
            }

            @Override
            public R related(Integer id) {
                return R.error();
            }
        };
    }
}
