package com.mouse.api.feign;

import com.mouse.api.hystrix.HystrixCatalogFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotNull;

/**
 * @author ; lidongdong
 * @Description 类目服务Api feign
 * @Date 2019-11-30
 */
@FeignClient(name = "http://ROTOR-API",
        path = "rotor-api/catalog",
        fallbackFactory = HystrixCatalogFeign.class)
public interface CatalogFeign {

    @GetMapping("/getFirstCategory")
    Object getFirstCategory();

    @GetMapping("/getSecondCategory")
    Object getSecondCategory(@NotNull Integer id);

    /**
     * 分类详情
     *
     * @param id 分类类目ID。
     *           如果分类类目ID是空，则选择第一个分类类目。
     *           需要注意，这里分类类目是一级类目
     * @return 分类详情
     */
    @GetMapping("index")
    Object index(Integer id);

    /**
     * 所有分类数据
     *
     * @return 所有分类数据
     */
    @GetMapping("all")
    Object queryAll();

    /**
     * 当前分类栏目
     *
     * @param id 分类类目ID
     * @return 当前分类栏目
     */
    @GetMapping("current")
    Object current(@NotNull Integer id);
}
