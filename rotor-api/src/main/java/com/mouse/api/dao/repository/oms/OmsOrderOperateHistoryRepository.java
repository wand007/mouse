package com.mouse.api.dao.repository.oms;

import com.mouse.api.dao.entity.oms.OmsOrderOperateHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 订单操作历史记录
 * @Date 2019-11-26
 */
@Repository
public interface OmsOrderOperateHistoryRepository extends JpaRepository<OmsOrderOperateHistoryEntity, Long>, JpaSpecificationExecutor<OmsOrderOperateHistoryEntity> {

}