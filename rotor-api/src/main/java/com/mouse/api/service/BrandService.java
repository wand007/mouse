package com.mouse.api.service;

import com.mouse.dao.entity.resource.BrandEntity;
import org.springframework.data.domain.Page;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-15
 */
public interface BrandService {
    Page<BrandEntity> findPage(Integer pageNum, Integer pageSize);

}
