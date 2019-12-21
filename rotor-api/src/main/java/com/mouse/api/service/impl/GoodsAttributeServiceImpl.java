package com.mouse.api.service.impl;

import com.mouse.api.service.GoodsAttributeService;
import com.mouse.dao.entity.resource.GoodsAttributeEntity;
import com.mouse.dao.repository.resource.GoodsAttributeRepository;
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
public class GoodsAttributeServiceImpl implements GoodsAttributeService {
    @Autowired
    GoodsAttributeRepository goodsAttributeRepository;


    @Override
    public Optional<List<GoodsAttributeEntity>> findByGoodsId(Integer goodsId) {
        return goodsAttributeRepository.findByGoodsIdAndDeleted(goodsId,false);
    }
}
