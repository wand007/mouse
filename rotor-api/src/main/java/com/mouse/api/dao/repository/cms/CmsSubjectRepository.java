package com.mouse.api.dao.repository.cms;

import com.mouse.api.dao.entity.cms.CmsSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 专题表
 * @Date 2019-11-26
 */
@Repository
public interface CmsSubjectRepository extends JpaRepository<CmsSubjectEntity, Long>, JpaSpecificationExecutor<CmsSubjectEntity> {


}