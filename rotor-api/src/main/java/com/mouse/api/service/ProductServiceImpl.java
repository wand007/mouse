package com.mouse.api.service;

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

    /**
     * 根据商品ID查询商品货品表
     * @param goodsId 商品ID
     * @return
     */
    @Override
    public Optional<List<GoodsProductEntity>> findByGoodsId(Integer goodsId) {
        return goodsProductRepository.findByGoodsIdAndDeleted(goodsId,false);
    }
}
