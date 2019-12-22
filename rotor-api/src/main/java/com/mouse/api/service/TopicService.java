package com.mouse.api.service;

import com.mouse.dao.entity.operate.TopicEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-15
 */
public interface TopicService {
    Page<TopicEntity> findPage(Integer id,Integer pageNum, Integer pageSize);

    Optional<TopicEntity> findById(Integer id);

}
