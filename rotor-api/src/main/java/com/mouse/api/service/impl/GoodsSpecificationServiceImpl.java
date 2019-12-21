package com.mouse.api.service.impl;

import com.mouse.api.service.GoodsSpecificationService;
import com.mouse.dao.entity.resource.GoodsSpecificationEntity;
import com.mouse.dao.repository.resource.GoodsSpecificationRepository;
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
public class GoodsSpecificationServiceImpl implements GoodsSpecificationService {
    @Autowired
    GoodsSpecificationRepository goodsSpecificationRepository;

    /**
     * 根据商品ID查询商品规格
     *
     * @param goodsId 商品ID
     * @return
     */
    @Override
    public Optional<List<GoodsSpecificationEntity>> findByGoodsId(Integer goodsId) {
        return goodsSpecificationRepository.findByGoodsIdAndDeleted(goodsId, false);
    }
}
