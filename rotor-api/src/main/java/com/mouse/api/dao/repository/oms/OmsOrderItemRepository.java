package com.mouse.api.dao.repository.oms;

import com.mouse.api.dao.entity.oms.OmsOrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 订单中所包含的商品
 * @Date 2019-11-26
 */
@Repository
public interface OmsOrderItemRepository extends JpaRepository<OmsOrderItemEntity, Long>, JpaSpecificationExecutor<OmsOrderItemEntity> {

}