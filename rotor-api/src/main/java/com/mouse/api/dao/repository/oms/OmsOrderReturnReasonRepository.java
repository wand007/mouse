package com.mouse.api.dao.repository.oms;

import com.mouse.api.dao.entity.oms.OmsOrderReturnReasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 退货原因表
 * @Date 2019-11-26
 */
@Repository
public interface OmsOrderReturnReasonRepository extends JpaRepository<OmsOrderReturnReasonEntity, Long>, JpaSpecificationExecutor<OmsOrderReturnReasonEntity> {

}