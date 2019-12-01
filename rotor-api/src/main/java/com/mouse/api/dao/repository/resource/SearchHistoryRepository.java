package com.mouse.api.dao.repository.resource;

import com.mouse.api.dao.entity.resource.SearchHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 搜素历史
 * @Date 2019-11-26
 */
@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistoryEntity, Integer>, JpaSpecificationExecutor<SearchHistoryEntity> {
}
