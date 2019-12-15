package com.mouse.api.service.impl;

import com.mouse.api.commons.enums.PositionEnum;
import com.mouse.api.service.AdService;
import com.mouse.dao.entity.operate.AdEntity;
import com.mouse.dao.repository.operate.AdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description 广告表
 * @Date 2019-12-15
 */
@Slf4j
@Service
public class AdServiceImpl implements AdService {
    @Autowired
    AdRepository adRepository;

    /**
     * 查询广告列表
     *
     * @return
     */
    @Override
    public Optional<List<AdEntity>> findIndex() {
        return adRepository.findByPositionAndEnabledAndDeleted(PositionEnum.HOME.getCode(), true, false);
    }
}
