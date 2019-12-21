package com.mouse.dao.repository.sys;

import com.mouse.dao.entity.sys.KeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface KeywordRepository extends JpaRepository<KeywordEntity, Integer>, JpaSpecificationExecutor<KeywordEntity> {

    Optional<KeywordEntity> findByIsDefaultAndDeleted(Boolean isDefault, Boolean deleted);

    Optional<List<KeywordEntity>> findByisHotAndDeleted(Boolean isHots, Boolean deleted);
}