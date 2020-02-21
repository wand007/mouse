package com.mouse.api.service.impl;

import com.mouse.api.service.ProductService;
import com.mouse.dao.entity.resource.GoodsProductEntity;
import com.mouse.dao.repository.resource.GoodsProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    GoodsProductRepository goodsProductRepository;


    @Override
    public Optional<GoodsProductEntity> findById(Integer productId) {
        return goodsProductRepository.findById(productId);
    }

    /**
     * 根据商品ID查询商品货品表
     *
     * @param goodsId 商品ID
     * @return
     */
    @Override
    public Optional<List<GoodsProductEntity>> findByGoodsId(Integer goodsId) {
        return goodsProductRepository.findByGoodsIdAndDeleted(goodsId, false);
    }

    @Override
    public Integer reduceStock(Integer productId, Short number) {
        return goodsProductRepository.updateStock(productId, number);
    }
}
