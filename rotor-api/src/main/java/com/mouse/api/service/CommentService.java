package com.mouse.api.service;

import com.mouse.dao.entity.user.CommentEntity;
import org.springframework.data.domain.Page;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
public interface CommentService {
    Page<CommentEntity> findByValueIdPage(Integer valueId, Integer pageNum, Integer pageSize);
}
