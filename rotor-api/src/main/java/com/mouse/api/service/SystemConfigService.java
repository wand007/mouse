package com.mouse.api.service;

import com.mouse.dao.entity.sys.SystemEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-15
 */
public interface SystemConfigService {
    /**
     * 获取全部未删除的系统配置
     * @return
     */
    Optional<List<SystemEntity>> findAll();

    /**
     * 新增系统配置
     * @param key
     * @param value
     */
    void addConfig(String key, String value);
}
