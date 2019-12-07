package com.mouse.api.dao.repository.cms;

import com.mouse.api.dao.entity.cms.CmsTopicCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 话题分类表
 * @Date 2019-11-26
 */
@Repository
public interface CmsTopicCategoryRepository extends JpaRepository<CmsTopicCategoryEntity, Long>, JpaSpecificationExecutor<CmsTopicCategoryEntity> {
}