package com.mouse.api.service;

import com.mouse.api.commons.enums.RefererEnum;
import com.mouse.dao.entity.sys.SearchHistoryEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
public interface SearchHistoryService {
    void asyncSave(String userId, String keyword, RefererEnum refererEnum);

    Optional<List<SearchHistoryEntity>> findByUid(String userId);

    void deleteByUserId(String userId);
}
