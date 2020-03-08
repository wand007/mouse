package com.mouse.web.controller;

import com.mouse.api.feign.CatalogFeign;
import com.mouse.core.base.R;
import com.mouse.web.base.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ; lidongdong
 * @Description 类目服务
 * @Date 2019-11-30
 */

@Slf4j
@Validated
@RestController
@RequestMapping("catalog")
public class CatalogController extends GlobalExceptionHandler {

    @Autowired
    CatalogFeign catalogFeign;

    @GetMapping("/findFirstCategory")
    public R findFirstCategory() {
        return catalogFeign.findFirstCategory();
    }

    @GetMapping("/findSecondCategory")
    public R findSecondCategory(@RequestParam("id") Integer id) {
        return catalogFeign.findSecondCategory(id);
    }

    /**
     * 分类详情
     *
     * @param id 分类类目ID。
     *           如果分类类目ID是空，则选择第一个分类类目。
     *           需要注意，这里分类类目是一级类目
     * @return 分类详情
     */
    @GetMapping("index")
    public R index(@RequestParam(value = "id", required = false) Integer id) {
        return catalogFeign.index(id);
    }

    /**
     * 所有分类数据
     *
     * @return 所有分类数据
     */
    @GetMapping("findAll")
    public R findAll() {
        return catalogFeign.findAll();
    }

    /**
     * 当前分类栏目
     *
     * @param id 分类类目ID
     * @return 当前分类栏目
     */
    @GetMapping("current")
    public R current(@RequestParam("id") Integer id) {
        return catalogFeign.current(id);
    }
}
