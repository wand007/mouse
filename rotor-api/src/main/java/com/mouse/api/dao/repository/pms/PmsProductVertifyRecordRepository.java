package com.mouse.api.dao.repository.pms;

import com.mouse.api.dao.entity.pms.PmsProductVertifyRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 商品审核记录
 * @Date 2019-11-26
 */
@Repository
public interface PmsProductVertifyRecordRepository extends JpaRepository<PmsProductVertifyRecordEntity, Long>, JpaSpecificationExecutor<PmsProductVertifyRecordEntity> {

}