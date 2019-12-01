package com.mouse.api.dao.repository.resource;

import com.mouse.api.dao.entity.resource.SpecificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 规格表
 * @Date 2019-11-26
 */
@Repository
public interface SpecificationRepository extends JpaRepository<SpecificationEntity, Integer>, JpaSpecificationExecutor<SpecificationEntity> {

}
