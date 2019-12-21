package com.mouse.api.service;

import com.mouse.dao.entity.sys.KeywordEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
public interface KeywordService {
    Optional<KeywordEntity> findDefault();

    Optional<List<KeywordEntity>> findIsHots();

    Page<KeywordEntity> findByKeyword(String keyword, Integer pageNum, Integer pageSize);
}
