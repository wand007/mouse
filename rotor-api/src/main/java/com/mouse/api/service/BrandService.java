package com.mouse.api.service;

import com.mouse.dao.entity.resource.BrandEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-15
 */
public interface BrandService {
    Page<BrandEntity> findPage(Integer pageNum, Integer pageSize);

    List<BrandEntity> findByGoodsId(Integer brandId);

    Optional<BrandEntity> findById(Integer id);
}
