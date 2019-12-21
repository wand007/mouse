package com.mouse.api.service;

import com.mouse.api.commons.enums.RefererEnum;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
public interface SearchHistoryService {
    void asyncSave(Integer userId, String keyword, RefererEnum refererEnum);
}
