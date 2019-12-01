package com.mouse.api.dao.repository.sys;

import com.mouse.api.dao.entity.sys.SysSmsLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 发送短信日志
 * @Date 2019-11-26
 */
@Repository
public interface SysSmsLogRepository extends JpaRepository<SysSmsLogEntity, Integer>, JpaSpecificationExecutor<SysSmsLogEntity> {
}
