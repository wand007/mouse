package com.mouse.api.service.impl;

import com.mouse.api.service.CollectService;
import com.mouse.dao.repository.user.CollectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
@Slf4j
@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    CollectRepository collectRepository;


    @Override
    public Integer countByUserIdAndValueId(Integer userId, Integer goodsId) {
        return collectRepository.countByUserIdAndValueIdAndDeleted(userId,goodsId,false);
    }
}
