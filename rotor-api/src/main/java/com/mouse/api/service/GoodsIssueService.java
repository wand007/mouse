package com.mouse.api.service;

import com.mouse.dao.entity.sys.IssueEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
public interface GoodsIssueService {
    /**
     * 查询问题列表
     *
     * @param question 标题
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    Page<IssueEntity> findByquestionPage(String question, Integer page, Integer limit, String sort, String order);
}
