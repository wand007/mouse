package com.mouse.api;

import com.mouse.api.service.CategoryService;
import com.mouse.api.service.GoodsService;
import com.mouse.api.system.SystemConfig;
import com.mouse.dao.entity.resource.CategoryEntity;
import com.mouse.dao.entity.resource.GoodsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-15
 */
@Slf4j
@Component
public class GoodsComm {
    @Autowired
    GoodsService goodsService;
    @Autowired
    CategoryService categoryService;

    public List<Map<String, Object>> getCategoryList() {
        List<Map<String, Object>> categoryList = new ArrayList<>();
        List<CategoryEntity> categoryEntities = categoryService.findByLevelAndNameNotAnd(0, SystemConfig.getCatlogListLimit()).orElseGet(() -> Arrays.asList());
        categoryEntities.stream().forEach(categoryEntity -> {
            List<CategoryEntity> entityList = categoryService.findByPid(categoryEntity.getId()).orElseGet(() -> Arrays.asList());
            List<Integer> categoryIds = entityList.stream().map(CategoryEntity::getId).collect(Collectors.toList());

            List<GoodsEntity> goodsEntities = new ArrayList<>();
            if (categoryIds.size() != 0) {
                goodsEntities = goodsService.findByCategory(categoryIds, 0, SystemConfig.getCatlogMoreLimit()).getContent();
            }

            Map<String, Object> map = new HashMap<>();
            map.put("id", categoryEntity.getId());
            map.put("name", categoryEntity.getName());
            map.put("goodsList", goodsEntities);
            categoryList.add(map);
        });
        return categoryList;
    }
}
