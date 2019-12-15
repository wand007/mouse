package com.mouse.api.service;

import com.mouse.dao.entity.resource.GoodsEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-15
 */
public interface GoodsService {
    /**
     * 查询新品和在售的商品
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<GoodsEntity> findByIsNewAndIsOnSaleAndPage(Integer pageNum, Integer pageSize);

    /**
     * 查询热销商品
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<GoodsEntity> findByIsHot(Integer pageNum, Integer pageSize);

    Page<GoodsEntity> findByCategory(List<Integer> categoryIds, Integer pageNum, Integer pageSize);
}
