package com.mouse.api.client;

import com.mouse.api.GoodsComm;
import com.mouse.api.base.BaseClient;
import com.mouse.api.commons.enums.CategoryLevelEnum;
import com.mouse.api.feign.CatalogFeign;
import com.mouse.api.service.*;
import com.mouse.core.base.R;
import com.mouse.dao.entity.resource.CategoryEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author ; lidongdong
 * @Description 类目服务 API
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("catalog")
public class CatalogClient extends BaseClient implements CatalogFeign {

    @Autowired
    GoodsComm goodsComm;

    @Autowired
    AdService adService;
    @Autowired
    TopicService topicService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    BrandService brandService;
    @Autowired
    CouponService couponService;
    @Autowired
    CategoryService categoryService;

    @Autowired
    GrouponService grouponService;


    @Override
    public R getFirstCategory() {
        // 所有一级分类目录
        List<CategoryEntity> categoryEntities = categoryService.findByLevel(CategoryLevelEnum.L1).orElseGet(() -> Arrays.asList());
        return R.success(categoryEntities);
    }

    @Override
    public R getSecondCategory(@NotNull Integer id) {
        // 所有二级分类目录
        List<CategoryEntity> categoryEntities = categoryService.findByPid(id).orElseGet(() -> Arrays.asList());
        return R.success(categoryEntities);
    }

    @Override
    public R index(Integer id) {
        // 所有一级分类目录
        Optional<List<CategoryEntity>> categoryEntitiesOptional = categoryService.findByLevel(CategoryLevelEnum.L1);
        Map<String, Object> data = new HashMap<String, Object>(8);
        if (!categoryEntitiesOptional.isPresent()) {
            return R.success(data);
        }
        List<CategoryEntity> categoryEntities = categoryEntitiesOptional.get();
        // 当前一级分类目录
        CategoryEntity currentCategory = null;
        if (id != null) {
            currentCategory = categoryService.findById(id).orElseGet(() -> new CategoryEntity());
        } else {
            currentCategory = categoryEntities.get(0);
        }

        // 当前一级分类目录对应的二级分类目录
        List<CategoryEntity> currentSubCategory = null;
        if (null != currentCategory) {
            currentSubCategory = categoryService.findByPid(currentCategory.getId()).orElseGet(() -> Arrays.asList());
        }

        data.put("categoryList", categoryEntities);
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", currentSubCategory);
        return R.success(data);
    }

    @Override
    public R queryAll() {
        // 所有一级分类目录
        List<CategoryEntity> l1CatList = categoryService.findByLevel(CategoryLevelEnum.L1).orElseGet(() -> Arrays.asList());

        //所有子分类列表
        Map<Integer, List<CategoryEntity>> allList = new HashMap<>();
        List<CategoryEntity> sub;
        for (CategoryEntity category : l1CatList) {
            sub = categoryService.findByPid(category.getId()).orElseGet(() -> Arrays.asList());
            allList.put(category.getId(), sub);
        }

        // 当前一级分类目录
        CategoryEntity currentCategory = l1CatList.get(0);

        // 当前一级分类目录对应的二级分类目录
        List<CategoryEntity> currentSubCategory = null;
        if (null != currentCategory) {
            currentSubCategory = categoryService.findByPid(currentCategory.getId()).orElseGet(() -> Arrays.asList());
        }

        Map<String, Object> data = new HashMap<String, Object>(8);
        data.put("categoryList", l1CatList);
        data.put("allList", allList);
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", currentSubCategory);

        return R.success(data);
    }

    @Override
    public R current(@NotNull Integer id) {
        Map<String, Object> data = new HashMap<String, Object>(8);
        // 当前分类
        Optional<CategoryEntity> categoryEntityOptional = categoryService.findById(id);
        if (!categoryEntityOptional.isPresent()) {
            return R.success(data);
        }
        CategoryEntity categoryEntity = categoryEntityOptional.get();
        List<CategoryEntity> currentSubCategory = categoryService.findByPid(categoryEntity.getId()).orElseGet(() -> Arrays.asList());

        data.put("currentCategory", categoryEntity);
        data.put("currentSubCategory", currentSubCategory);
        return R.success(data);
    }
}
