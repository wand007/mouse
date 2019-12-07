package com.mouse.api.dao.repository.cms;

import com.mouse.api.dao.entity.cms.CmsPrefrenceAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 优选专区
 * @Date 2019-11-26
 */
@Repository
public interface CmsPrefrenceAreaRepository extends JpaRepository<CmsPrefrenceAreaEntity, Long>, JpaSpecificationExecutor<CmsPrefrenceAreaEntity> {
}