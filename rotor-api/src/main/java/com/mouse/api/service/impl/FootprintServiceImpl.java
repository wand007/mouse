package com.mouse.api.service.impl;

import com.mouse.api.service.FootprintService;
import com.mouse.dao.entity.user.FootprintEntity;
import com.mouse.dao.repository.user.FootprintRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
@Slf4j
@Service
public class FootprintServiceImpl implements FootprintService {
    @Autowired
    FootprintRepository footprintRepository;

    @Async
    @Override
    public void asyncSave(Integer userId, Integer goodsId) {
        FootprintEntity footprint = new FootprintEntity();
        footprint.setUserId(userId);
        footprint.setGoodsId(goodsId);
        footprint.setDeleted(false);
        footprintRepository.save(footprint);
    }
}
