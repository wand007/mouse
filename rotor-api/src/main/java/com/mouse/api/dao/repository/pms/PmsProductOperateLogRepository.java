package com.mouse.api.dao.repository.pms;

import com.mouse.api.dao.entity.pms.PmsProductOperateLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 商品操作日志(只针对同商品)
 * @Date 2019-11-26
 */
@Repository
public interface PmsProductOperateLogRepository extends JpaRepository<PmsProductOperateLogEntity, Long>, JpaSpecificationExecutor<PmsProductOperateLogEntity> {

}