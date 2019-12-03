package com.mouse.admin.controller;

import com.mouse.admin.base.BaseController;
import com.mouse.api.feign.ResourcesFeign;
import com.mouse.core.base.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 资源
 * @Date 2019-11-30
 */
@Slf4j
@Validated
@RestController
@RequestMapping("resources")
public class ResourcesController extends BaseController {

    @Autowired
    private ResourcesFeign resourcesFeign;

    /**
     * 分页查询商品列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @GetMapping(value = "findPage")
    public R findPage(
            @Min(value = 0, message = "必须从0页开始")
            @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
            @Min(value = 1, message = "每页必须大于1")
            @Max(value = 300, message = "每页必须小于300")
            @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        return resourcesFeign.findPage(pageNum, pageSize);
    }
}
