package com.mouse.api.dao.repository.oms;

import com.mouse.api.dao.entity.oms.OmsOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 订单表
 * @Date 2019-11-26
 */
@Repository
public interface OmsOrderRepository extends JpaRepository<OmsOrderEntity, Long>, JpaSpecificationExecutor<OmsOrderEntity> {

}