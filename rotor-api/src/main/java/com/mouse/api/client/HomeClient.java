package com.mouse.api.client;

import com.mouse.api.GoodsComm;
import com.mouse.api.base.BaseClient;
import com.mouse.api.commons.enums.CategoryLevelEnum;
import com.mouse.api.feign.HomeFeign;
import com.mouse.api.service.*;
import com.mouse.api.system.SystemConfig;
import com.mouse.core.base.R;
import com.mouse.dao.entity.operate.AdEntity;
import com.mouse.dao.entity.operate.CouponEntity;
import com.mouse.dao.entity.operate.GrouponEntity;
import com.mouse.dao.entity.operate.TopicEntity;
import com.mouse.dao.entity.resource.BrandEntity;
import com.mouse.dao.entity.resource.CategoryEntity;
import com.mouse.dao.entity.resource.GoodsEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ; lidongdong
 * @Description 首页服务 API
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("home")
public class HomeClient extends BaseClient implements HomeFeign {

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


    /**
     * 首页数据
     * 当用户已经登录时，非空。为登录状态为null
     *
     * @param userId 登陆用户ID
     * @return
     */
    @Override
    public R index(String userId) {
        Page<CouponEntity> couponEntityPage;
        if (StringUtils.isBlank(userId)) {
            couponEntityPage = couponService.findPage(0, 3);
        } else {
            couponEntityPage = couponService.findByUserIdPage(userId, 0, 3);
        }

        List<AdEntity> adEntities = adService.findIndex().orElseGet(() -> Arrays.asList());

        List<CategoryEntity> categoryEntities = categoryService.findByLevel(CategoryLevelEnum.L1).orElseGet(() -> Arrays.asList());

        Page<GoodsEntity> newGoodsEntitiesPage = goodsService.findByIsNewAndIsOnSaleAndPage(0, SystemConfig.getNewLimit());

        Page<GoodsEntity> hotGoodsEntitiesPage = goodsService.findByIsHot(0, SystemConfig.getHotLimit());

        Page<BrandEntity> brandEntityPage = brandService.findPage(0, SystemConfig.getBrandLimit());

        Page<TopicEntity> topicEntityPage = topicService.findPage(0, SystemConfig.getTopicLimit());

        List<Map<String, Object>> floorGoodsList = goodsComm.getCategoryList();
        //团购专区
        Page<GrouponEntity> grouponEntityPage = grouponService.findPage(0, 5);

        Map<String, Object> entity = new HashMap<>(16);
        entity.put("banner", adEntities);
        entity.put("channel", categoryEntities);
        entity.put("couponList", couponEntityPage.getContent());
        entity.put("newGoodsList", newGoodsEntitiesPage.getContent());
        entity.put("hotGoodsList", hotGoodsEntitiesPage.getContent());
        entity.put("brandList", brandEntityPage.getContent());
        entity.put("topicList", topicEntityPage.getContent());
        entity.put("grouponList", grouponEntityPage.getContent());
        entity.put("floorGoodsList", floorGoodsList);
        return R.success(entity);
    }
}
