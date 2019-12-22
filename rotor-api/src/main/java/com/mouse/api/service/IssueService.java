package com.mouse.api.service;

import com.mouse.dao.entity.operate.AdEntity;
import com.mouse.dao.entity.sys.IssueEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-15
 */
public interface IssueService {


    Page<IssueEntity> findPage(String question, Integer pageNum, Integer pageSize);
}
