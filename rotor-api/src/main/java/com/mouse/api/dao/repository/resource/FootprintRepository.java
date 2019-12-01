package com.mouse.api.dao.repository.resource;

import com.mouse.api.dao.entity.resource.FootprintEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface FootprintRepository extends JpaRepository<FootprintEntity, Integer>, JpaSpecificationExecutor<FootprintEntity> {
}
