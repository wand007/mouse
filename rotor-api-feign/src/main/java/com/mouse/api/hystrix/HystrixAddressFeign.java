package com.mouse.api.hystrix;

import com.mouse.api.commons.req.SaveAddressReq;
import com.mouse.api.feign.AddressFeign;
import com.mouse.core.base.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 用户收货地址服务 熔断器
 * @Date 2019-11-30
 */
@Slf4j
@Component
public class HystrixAddressFeign implements FallbackFactory<AddressFeign> {
    @Override
    public AddressFeign create(Throwable throwable) {

        log.error("错误信息：", throwable);
        return new AddressFeign() {
            @Override
            public R findPage(Integer userId, @Min(value = 0, message = "必须从0页开始") Integer pageNum, @Min(value = 1, message = "每页必须大于1") @Max(value = 300, message = "每页必须小于300") Integer pageSize) {
                return R.error();
            }

            @Override
            public R detail(Integer userId, Integer id) {
                return R.error();
            }

            @Override
            public R save(SaveAddressReq param) {
                return R.error();
            }

            @Override
            public R update(SaveAddressReq param) {
                return R.error();
            }

            @Override
            public R delete(@RequestParam(name = "userId") Integer userId,
                            @RequestParam(name = "id") Integer id) {
                return R.error();
            }
        };
    }
}
