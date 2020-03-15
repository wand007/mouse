package com.mouse.api.commons;

import com.mouse.api.service.CartService;
import com.mouse.api.service.CategoryService;
import com.mouse.api.service.GoodsService;
import com.mouse.api.system.SystemConfig;
import com.mouse.dao.entity.resource.CategoryEntity;
import com.mouse.dao.entity.resource.GoodsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
    @Autowired
    CartService cartService;


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

    /**
     * 验证购物车商品是否再售 -- 异步
     *
     * @param goodsId 商品ID
     * @param cartId  购物车记录ID
     */
    @Async
    public void asyncCheckIsOnSale(Integer goodsId, Integer cartId) {
        GoodsEntity goodsEntity = goodsService.findById(goodsId).orElseGet(() -> {
            GoodsEntity goods = new GoodsEntity();
            goods.setIsOnSale(false);
            return goods;
        });
        if (!goodsEntity.getIsOnSale()) {
            cartService.deleteById(cartId);
            log.debug("系统自动删除失效购物车商品 goodsId=" + goodsId);
        }
    }
}
