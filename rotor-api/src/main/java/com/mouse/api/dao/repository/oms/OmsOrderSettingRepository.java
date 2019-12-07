package com.mouse.api.dao.repository.oms;

import com.mouse.api.dao.entity.oms.OmsOrderSettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 订单设置表
 * @Date 2019-11-26
 */
@Repository
public interface OmsOrderSettingRepository extends JpaRepository<OmsOrderSettingEntity, Long>, JpaSpecificationExecutor<OmsOrderSettingEntity> {

}