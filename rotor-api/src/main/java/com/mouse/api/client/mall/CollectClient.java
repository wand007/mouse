package com.mouse.api.client.mall;

import com.mouse.api.base.GlobalExceptionHandler;
import com.mouse.api.feign.mall.CollectFeign;
import com.mouse.api.service.CollectService;
import com.mouse.api.service.GoodsService;
import com.mouse.core.base.R;
import com.mouse.core.utils.PageNation;
import com.mouse.dao.entity.resource.GoodsEntity;
import com.mouse.dao.entity.user.CollectEntity;
import lombok.extern.slf4j.Slf4j;
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
 * @Description 用户收藏服务 API
 * @Date 2020-01-12
 */
@Slf4j
@Validated
@RestController
@RequestMapping("collect")
public class CollectClient extends GlobalExceptionHandler implements CollectFeign {

    @Autowired
    CollectService collectService;
    @Autowired
    GoodsService goodsService;

    /**
     * 用户收藏列表
     *
     * @param userId   用户ID
     * @param type     类型，如果是0则是商品收藏，如果是1则是专题收藏
     * @param pageNum  分页页数
     * @param pageSize 分页大小
     * @return
     */
    @Override
    public R findPage(@RequestParam(name = "userId") String userId,
                      @RequestParam(name = "type") Byte type,
                      @Min(value = 0, message = "必须从0页开始")
                      @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                      @Min(value = 1, message = "每页必须大于1")
                      @Max(value = 300, message = "每页必须小于300")
                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {

        Page<CollectEntity> page = collectService.findPage(userId, type, pageNum, pageSize);
        List<CollectEntity> content = page.getContent();

        List<Map<String, Object>> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(content)) {
            return R.success(PageNation.of(page, result));
        }
        List<Integer> valueIds = content.stream().map(CollectEntity::getValueId).collect(Collectors.toList());
        List<GoodsEntity> goodsEntities = goodsService.findByIds(valueIds).orElseGet(() -> new ArrayList());
        Map<Integer, GoodsEntity> goodsMap = goodsEntities.stream().collect(Collectors.toMap(GoodsEntity::getId, a -> a, (k1, k2) -> k1));

        for (CollectEntity collect : content) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", collect.getId());
            map.put("type", collect.getType());
            map.put("valueId", collect.getValueId());

            GoodsEntity goodsEntity = goodsMap.get(collect.getValueId());
            map.put("name", goodsEntity.getName());
            map.put("brief", goodsEntity.getBrief());
            map.put("picUrl", goodsEntity.getPicUrl());
            map.put("retailPrice", goodsEntity.getRetailPrice());
            result.add(map);
        }
        return R.success(PageNation.of(page, result));
    }

    /**
     * 用户收藏添加或删除
     * <p>
     * 如果商品没有收藏，则添加收藏；如果商品已经收藏，则删除收藏状态。
     *
     * @param userId  用户ID
     * @param type    收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID
     * @param valueId 如果type=0，则是商品ID；如果type=1，则是专题ID
     * @return 操作结果
     */
    @Override
    public R addOrDelete(@RequestParam(name = "userId") String userId,
                         @RequestParam(name = "type") Integer type,
                         @RequestParam(name = "valueId") Integer valueId) {

        Optional<CollectEntity> collectEntityOptional = collectService.findByUserIdAndValueIdAndType(userId, valueId, type);
        if (collectEntityOptional.isPresent()) {
            CollectEntity collectEntity = collectEntityOptional.get();
            collectService.deleteById(collectEntity.getId());
        } else {
            collectService.save(userId, type, valueId);
        }
        return R.success();
    }
}
