package com.mouse.api.service;

import com.mouse.dao.entity.resource.GoodsProductEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
public interface ProductService {
    /**
     * 根据商品ID查询商品货品表
     * @param goodsId 商品ID
     * @return
     */
    Optional<List<GoodsProductEntity>> findByGoodsId(Integer goodsId);
}
