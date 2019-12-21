package com.mouse.dao.repository.sys;

import com.mouse.dao.entity.sys.SearchHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistoryEntity, Integer>, JpaSpecificationExecutor<SearchHistoryEntity> {

    Optional<List<SearchHistoryEntity>> findByUserIdAndDeleted(Integer userId, Boolean deleted);

    @Transactional
    @Modifying
    @Query(value = "delete from SearchHistoryEntity rc where rc.userId = ?1 ")
    Integer deleteByUserId(Integer userId);
}