package com.mouse.api.dao.repository.ums;

import com.mouse.api.dao.entity.ums.UmsMemberStatisticsInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 会员统计信息
 * @Date 2019-11-26
 */
@Repository
public interface UmsMemberStatisticsInfoRepository extends JpaRepository<UmsMemberStatisticsInfoEntity, Long>, JpaSpecificationExecutor<UmsMemberStatisticsInfoEntity> {
}