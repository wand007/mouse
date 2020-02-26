package com.mouse.web.controller;

import com.mouse.api.feign.BrandFeign;
import com.mouse.core.base.R;
import com.mouse.web.base.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author ; lidongdong
 * @Description 专题服务
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("brand")
public class BrandController extends GlobalExceptionHandler {
    @Autowired
    BrandFeign brandFeign;

    /**
     * 品牌列表
     *
     * @param pageNum
     * @param pageSize
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("findPage")
    public R findPage(@Min(value = 0, message = "必须从0页开始")
                      @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                      @Min(value = 1, message = "每页必须大于1")
                      @Max(value = 300, message = "每页必须小于300")
                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                      @RequestParam(defaultValue = "add_time", required = false) String sort,
                      @RequestParam(defaultValue = "desc", required = false) String order) {
        return brandFeign.findPage(pageNum, pageSize, sort, order);
    }

    /**
     * 品牌详情
     *
     * @param id 品牌ID
     * @return 品牌详情
     */
    @GetMapping("detail")
    public R detail(@NotNull Integer id) {
        return brandFeign.detail(id);
    }
}

