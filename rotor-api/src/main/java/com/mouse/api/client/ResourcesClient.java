package com.mouse.api.client;

import com.mouse.api.base.BaseClient;
import com.mouse.api.feign.ResourcesFeign;
import com.mouse.core.base.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public R findPage(
            @Min(value = 0, message = "必须从0页开始")
            @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
            @Min(value = 1, message = "每页必须大于1")
            @Max(value = 300, message = "每页必须小于300")
            @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        return R.success();
    }
}
