package com.mouse.api.dao.repository.cms;

import com.mouse.api.dao.entity.cms.CmsPrefrenceAreaProductRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 优选专区和产品关系表
 * @Date 2019-11-26
 */
@Repository
public interface CmsPrefrenceAreaProductRelationRepository extends JpaRepository<CmsPrefrenceAreaProductRelationEntity, Long>, JpaSpecificationExecutor<CmsPrefrenceAreaProductRelationEntity> {

}