package com.mouse.api.dao.repository.sys;

import com.mouse.api.dao.entity.sys.SysLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 系统日志
 * @Date 2019-11-26
 */
@Repository
public interface SysLogRepository extends JpaRepository<SysLogEntity, Integer>, JpaSpecificationExecutor<SysLogEntity> {
}
