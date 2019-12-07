package com.mouse.api.dao.repository.ums;

import com.mouse.api.dao.entity.ums.UmsGrowthChangeHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 成长值变化历史记录表
 * @Date 2019-11-26
 */
@Repository
public interface UmsGrowthChangeHistoryRepository extends JpaRepository<UmsGrowthChangeHistoryEntity, Long>, JpaSpecificationExecutor<UmsGrowthChangeHistoryEntity> {


}