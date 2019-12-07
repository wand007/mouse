package com.mouse.api.dao.repository.ums;

import com.mouse.api.dao.entity.ums.UmsIntegrationConsumeSettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 积分消费设置
 * @Date 2019-11-26
 */
@Repository
public interface UmsIntegrationConsumeSettingRepository extends JpaRepository<UmsIntegrationConsumeSettingEntity, Long>, JpaSpecificationExecutor<UmsIntegrationConsumeSettingEntity> {

}