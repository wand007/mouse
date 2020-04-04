package com.mouse.api.hystrix.mall;

import com.mouse.api.commons.req.SaveCommentReq;
import com.mouse.api.feign.mall.CommentFeign;
import com.mouse.core.base.BusinessCode;
import com.mouse.core.base.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 用户评论服务 熔断器
 * @Date 2020-01-12
 */
@Slf4j
@Component
public class HystrixCommentFeign implements FallbackFactory<CommentFeign> {
    @Override
    public CommentFeign create(Throwable throwable) {

        log.error("错误信息：", throwable);
        return new CommentFeign() {
            @Override
            public R post(String userId, SaveCommentReq param) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R count(Integer type, Integer valueId) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }

            @Override
            public R findPage(Integer valueId, Integer type, Integer showType, @Min(value = 0, message = "必须从0页开始") Integer pageNum, @Min(value = 1, message = "每页必须大于1") @Max(value = 300, message = "每页必须小于300") Integer pageSize) {
                return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
            }
        };
    }
}
