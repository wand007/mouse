package com.mouse.api.client;

import com.mouse.api.GoodsComm;
import com.mouse.api.base.BaseClient;
import com.mouse.api.commons.enums.RefererEnum;
import com.mouse.api.feign.ResourcesFeign;
import com.mouse.api.service.CategoryService;
import com.mouse.api.service.GoodsService;
import com.mouse.api.service.SearchHistoryService;
import com.mouse.core.base.R;
import com.mouse.dao.entity.resource.CategoryEntity;
import com.mouse.dao.entity.resource.GoodsEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ; lidongdong
 * @Description 资源 Api
 * @Date 2019-11-30
 */
@Slf4j
@Validated
@RestController
@RequestMapping("resources")
public class ResourcesClient extends BaseClient implements ResourcesFeign {

    @Autowired
    GoodsComm goodsComm;
    @Autowired
    GoodsService goodsService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    SearchHistoryService searchHistoryService;

    /**
     * 分页查询商品列表
     * 1. 这里的前五个参数都是可选的，甚至都是空
     * 2. 用户是可选登录，如果登录，则记录用户的搜索关键字
     *
     * @param categoryId 分类类目ID，可选
     * @param brandId    品牌商ID，可选
     * @param keyword    关键字，可选
     * @param isNew      是否新品，可选
     * @param isHot      是否热买，可选
     * @param userId     用户ID
     * @param referer    来源
     * @param pageNum
     * @param pageSize
     * @param sort       排序方式，支持"add_time", "retail_price"或"name"
     * @param order      排序类型，顺序或者降序
     * @return 根据条件搜素的商品详情
     */
    @Override
    public R findPage(@RequestParam(name = "categoryId", required = false) Integer categoryId,
                      @RequestParam(name = "brandId", required = false) Integer brandId,
                      @RequestParam(name = "keyword", required = false) String keyword,
                      @RequestParam(name = "isNew", required = false) Boolean isNew,
                      @RequestParam(name = "isHot", required = false) Boolean isHot,
                      @RequestParam(name = "userId", required = false) Integer userId,
                      @RequestParam(name = "referer") Integer referer,
                      @Min(value = 0, message = "必须从0页开始")
                      @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                      @Min(value = 1, message = "每页必须大于1")
                      @Max(value = 300, message = "每页必须小于300")
                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                      @RequestParam(defaultValue = "add_time", required = false) String sort,
                      @RequestParam(defaultValue = "desc", required = false) String order) {
        //添加到搜索历史
        if (userId != null && !StringUtils.isNotBlank(keyword)) {
            searchHistoryService.asyncSave(userId, keyword, RefererEnum.parse(referer));
        }

        //查询列表数据
        Page<GoodsEntity> page = goodsService.findPage(categoryId, brandId, keyword, isHot, isNew, pageNum, pageSize, sort, order);

        // 查询商品所属类目列表。
        List<GoodsEntity> goodsEntities = goodsService.findList(brandId, keyword, isHot, isNew);
        List<CategoryEntity> categoryList = new ArrayList<>(0);
        if (!CollectionUtils.isEmpty(goodsEntities)) {
            List<Integer> categoryIds = goodsEntities.stream().map(GoodsEntity::getCategoryId).collect(Collectors.toList());
            categoryList = categoryService.findByLevelAndIdIn("L2", categoryIds).orElseGet(() -> Arrays.asList());
        }

        Map<String, Object> entity = new HashMap<>();
        entity.put("list", page);
        entity.put("total", page.getTotalElements());
        entity.put("page", page.getNumber());
        entity.put("limit", page.getNumberOfElements());
        entity.put("pages", page.getContent());
        entity.put("filterCategoryList", categoryList);

        // 因为这里需要返回额外的filterCategoryList参数，因此不能方便使用ResponseUtil.okList
        return R.success(entity);
    }

    @Override
    public R detail(Integer integer, Integer integer1) {
        return null;
    }

    @Override
    public R category(Integer integer) {
        return null;
    }

    @Override
    public R related(Integer integer) {
        return null;
    }

    @Override
    public R count() {
        return null;
    }
}
