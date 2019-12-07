package com.mouse.api.dao.repository.cms;

import com.mouse.api.dao.entity.cms.CmsMemberReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 用户举报表
 * @Date 2019-11-26
 */
@Repository
public interface CmsMemberReportRepository extends JpaRepository<CmsMemberReportEntity, Long>, JpaSpecificationExecutor<CmsMemberReportEntity> {

}