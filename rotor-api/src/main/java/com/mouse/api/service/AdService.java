package com.mouse.api.service;

import com.mouse.dao.entity.operate.AdEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description 广告表
 * @Date 2019-12-15
 */
public interface AdService {
    /**
     * 查询广告列表
     *
     * @return
     */
    Optional<List<AdEntity>> findIndex();


}
