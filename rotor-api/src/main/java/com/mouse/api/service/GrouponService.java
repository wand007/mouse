package com.mouse.api.service;

import com.mouse.dao.entity.operate.GrouponEntity;
import org.springframework.data.domain.Page;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-15
 */
public interface GrouponService {
    Page<GrouponEntity> findPage(Integer pageNum, Integer pageSize);
}
