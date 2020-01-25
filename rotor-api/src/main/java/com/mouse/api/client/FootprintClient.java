package com.mouse.api.client;

import com.mouse.api.base.BaseClient;
import com.mouse.api.feign.FootprintFeign;
import com.mouse.api.service.FootprintService;
import com.mouse.api.service.GoodsService;
import com.mouse.core.base.R;
import com.mouse.core.utils.PageNation;
import com.mouse.dao.entity.resource.GoodsEntity;
import com.mouse.dao.entity.user.FootprintEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ; lidongdong
 * @Description 用户访问足迹服务 API
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("footprint")
public class FootprintClient extends BaseClient implements FootprintFeign {

    @Autowired
    GoodsService goodsService;
    @Autowired
    FootprintService footprintService;

    /**
     * 删除用户访问记录
     *
     * @param id     记录ID
     * @param userId 用户ID
     * @return
     */
    @Override
    public R delete(@RequestParam(defaultValue = "userId") Integer userId,
                    @RequestParam(defaultValue = "id") String id) {

        footprintService.delete(userId, id);

        return R.success();
    }

    @Override
    public R findPage(@RequestParam(defaultValue = "userId") Integer userId,
                      @Min(value = 0, message = "必须从0页开始")
                      @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                      @Min(value = 1, message = "每页必须大于1")
                      @Max(value = 300, message = "每页必须小于300")
                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {

        Page<FootprintEntity> page = footprintService.findPage(userId, pageNum, pageSize);

        List<FootprintEntity> content = page.getContent();
        List<Integer> goodsIds = content.stream().map(FootprintEntity::getGoodsId).collect(Collectors.toList());
        List<GoodsEntity> goodsEntities = goodsService.findByIds(goodsIds).orElseGet(() -> new ArrayList<>());
        Map<Integer, GoodsEntity> goodsMap = goodsEntities.stream().collect(Collectors.toMap(GoodsEntity::getId, a -> a, (k1, k2) -> k1));

        List<Map<String, Object>> result = new ArrayList<>(page.getSize());
        for (FootprintEntity footprint : page.getContent()) {
            Map<String, Object> c = new HashMap<String, Object>(16);
            c.put("id", footprint.getId());
            c.put("goodsId", footprint.getGoodsId());
            c.put("addTime", footprint.getAddTime());
            GoodsEntity goodsEntity = goodsMap.get(footprint.getGoodsId());
            c.put("name", goodsEntity.getName());
            c.put("brief", goodsEntity.getBrief());
            c.put("picUrl", goodsEntity.getPicUrl());
            c.put("retailPrice", goodsEntity.getRetailPrice());
            result.add(c);
        }

        return R.success(PageNation.of(page, result));
    }
}
