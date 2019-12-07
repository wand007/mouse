package com.mouse.api.dao.repository.ums;

import com.mouse.api.dao.entity.ums.UmsIntegrationChangeHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 积分变化历史记录表
 * @Date 2019-11-26
 */
@Repository
public interface UmsIntegrationChangeHistoryRepository extends JpaRepository<UmsIntegrationChangeHistoryEntity, Long>, JpaSpecificationExecutor<UmsIntegrationChangeHistoryEntity> {


}