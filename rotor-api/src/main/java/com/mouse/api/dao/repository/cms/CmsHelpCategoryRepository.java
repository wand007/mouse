package com.mouse.api.dao.repository.cms;

import com.mouse.api.dao.entity.cms.CmsHelpCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-27
 */
@Repository
public interface CmsHelpCategoryRepository extends JpaRepository<CmsHelpCategoryEntity, Long>, JpaSpecificationExecutor<CmsHelpCategoryEntity> {
}
