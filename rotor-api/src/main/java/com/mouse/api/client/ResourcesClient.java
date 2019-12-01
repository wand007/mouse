package com.mouse.api.client;

import com.mouse.api.base.BaseClient;
import com.mouse.api.feign.ResourcesFeign;
import com.mouse.core.base.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 资源Api
 * @Date 2019-11-30
 */
@Slf4j
@Validated
@RestController
@RequestMapping("resources")
public class ResourcesClient extends BaseClient implements ResourcesFeign {
    @Override
    public R findPage(@Min(value = 0L, message = "必须从0页开始") Integer integer, @Min(value = 1L, message = "每页必须大于1") @Max(value = 300L, message = "每页必须小于300") Integer integer1) {
        return R.success();
    }
}
