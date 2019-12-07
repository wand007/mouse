package com.mouse.api.dao.repository.cms;

import com.mouse.api.dao.entity.cms.CmsHelpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 帮助表
 * @Date 2019-11-26
 */
@Repository
public interface CmsHelpRepository extends JpaRepository<CmsHelpEntity, Long>, JpaSpecificationExecutor<CmsHelpEntity> {

}