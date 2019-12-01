package com.mouse.api.dao.repository.operate;

import com.mouse.api.dao.entity.operate.AdPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface AdPositionRepository extends JpaRepository<AdPositionEntity, Integer>, JpaSpecificationExecutor<AdPositionEntity> {
}
