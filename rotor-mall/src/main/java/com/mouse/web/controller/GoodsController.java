package com.mouse.web.controller;

import com.mouse.api.commons.enums.RefererEnum;
import com.mouse.api.feign.mall.GoodsFeign;
import com.mouse.core.base.R;
import com.mouse.core.params.RotorSessionUser;
import com.mouse.web.base.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 商品服务服务
 * @Date 2020-01-25
 */

@Slf4j
@Validated
@RestController
@RequestMapping("goods")
public class GoodsController extends GlobalExceptionHandler {
    @Autowired
    GoodsFeign goodsFeign;

    /**
     * 商品详情
     * <p>
     * 用户可以不登录。
     * 如果用户登录，则记录用户足迹以及返回用户收藏信息。
     *
     * @param id 商品ID
     * @return 商品详情
     */
    @GetMapping("detail")
    public R detail(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestParam("id") Integer id) {
        return goodsFeign.detail(sessionUser.getId(), id);
    }

    /**
     * 商品分类类目
     *
     * @param id 分类类目ID
     * @return 商品分类类目
     */
    @GetMapping("category")
    public R category(@RequestParam("id") Integer id) {
        return goodsFeign.category(id);
    }

    /**
     * 根据条件搜素商品
     * <p>
     * 1. 这里的前五个参数都是可选的，甚至都是空
     * 2. 用户是可选登录，如果登录，则记录用户的搜索关键字
     *
     * @param categoryId 分类类目ID，可选
     * @param brandId    品牌商ID，可选
     * @param keyword    关键字，可选
     * @param isNew      是否新品，可选
     * @param isHot      是否热买，可选
     * @param pageNum    分页页数
     * @param pageSize   分页大小
     * @param sort       排序方式，支持"add_time", "retail_price"或"name"
     * @param order      排序类型，顺序或者降序
     * @return 根据条件搜素的商品详情
     */
    @GetMapping("findPage")
    public R findPage(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                      @RequestParam(name = "categoryId", required = false) Integer categoryId,
                      @RequestParam(name = "brandId", required = false) Integer brandId,
                      @RequestParam(name = "keyword", required = false) String keyword,
                      @RequestParam(name = "isNew", required = false) Boolean isNew,
                      @RequestParam(name = "isHot", required = false) Boolean isHot,
                      @Min(value = 0, message = "必须从0页开始")
                      @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                      @Min(value = 1, message = "每页必须大于1")
                      @Max(value = 300, message = "每页必须小于300")
                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                      @RequestParam(name = "sort", defaultValue = "add_time", required = false) String sort,
                      @RequestParam(name = "order", defaultValue = "desc", required = false) String order) {
        return goodsFeign.findPage(categoryId, brandId, keyword, isNew, isHot, sessionUser.getId(), RefererEnum.WX.getCode(), pageNum, pageSize, sort, order);
    }

    /**
     * 商品详情页面“大家都在看”推荐商品
     *
     * @param id, 商品ID
     * @return 商品详情页面推荐商品
     */
    @GetMapping("related")
    public R related(@RequestParam("id") Integer id) {
        return goodsFeign.related(id);
    }

    /**
     * 在售的商品总数
     *
     * @return 在售的商品总数
     */
    @GetMapping("count")
    public R count() {
        return goodsFeign.count();
    }

}