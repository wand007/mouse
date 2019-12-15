package com.mouse.api.service;

import com.mouse.dao.entity.operate.TopicEntity;
import org.springframework.data.domain.Page;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-15
 */
public interface TopicService {
    Page<TopicEntity> findPage(Integer pageNum, Integer pageSize);
}
