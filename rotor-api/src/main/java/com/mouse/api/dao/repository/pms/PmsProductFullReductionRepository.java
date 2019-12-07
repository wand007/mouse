package com.mouse.api.dao.repository.pms;

import com.mouse.api.dao.entity.pms.PmsProductFullReductionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 产品满减表(只针对同商品)
 * @Date 2019-11-26
 */
@Repository
public interface PmsProductFullReductionRepository extends JpaRepository<PmsProductFullReductionEntity, Long>, JpaSpecificationExecutor<PmsProductFullReductionEntity> {

}