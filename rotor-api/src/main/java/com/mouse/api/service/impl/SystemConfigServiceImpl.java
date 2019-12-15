package com.mouse.api.service.impl;

import com.mouse.api.service.SystemConfigService;
import com.mouse.dao.entity.sys.SystemEntity;
import com.mouse.dao.repository.sys.SystemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description 系统配置
 * @Date 2019-12-15
 */
@Slf4j
@Service
public class SystemConfigServiceImpl implements SystemConfigService {
    @Autowired
    SystemRepository systemRepository;

    /**
     * 获取全部未删除的系统配置
     *
     * @return
     */
    @Override
    public Optional<List<SystemEntity>> findAll() {
        return systemRepository.findByDeleted(false);
    }

    /**
     * 新增系统配置
     *
     * @param key
     * @param value
     */
    @Override
    public void addConfig(String key, String value) {
        SystemEntity system = new SystemEntity();
        system.setKeyName(key);
        system.setKeyValue(value);
        system.setAddTime(LocalDateTime.now());
        system.setUpdateTime(LocalDateTime.now());
        systemRepository.save(system);
    }
}
