package com.mouse.api.dao.repository.oms;

import com.mouse.api.dao.entity.oms.OmsCompanyAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 公司收发货地址表
 * @Date 2019-11-26
 */
@Repository
public interface OmsCompanyAddressRepository extends JpaRepository<OmsCompanyAddressEntity, Long>, JpaSpecificationExecutor<OmsCompanyAddressEntity> {

}