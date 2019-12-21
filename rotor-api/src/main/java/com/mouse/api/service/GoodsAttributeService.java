package com.mouse.api.service;

import com.mouse.dao.entity.resource.GoodsAttributeEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
public interface GoodsAttributeService {
    /**
     * 根据商品ID获取商品属性
     *
     * @param goodsId 商品ID
     * @return
     */
    Optional<List<GoodsAttributeEntity>> findByGoodsId(Integer goodsId);
}
