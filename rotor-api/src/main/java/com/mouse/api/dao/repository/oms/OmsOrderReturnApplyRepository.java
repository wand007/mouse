package com.mouse.api.dao.repository.oms;

import com.mouse.api.dao.entity.oms.OmsOrderReturnApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 订单退货申请
 * @Date 2019-11-26
 */
@Repository
public interface OmsOrderReturnApplyRepository extends JpaRepository<OmsOrderReturnApplyEntity, Long>, JpaSpecificationExecutor<OmsOrderReturnApplyEntity> {


}