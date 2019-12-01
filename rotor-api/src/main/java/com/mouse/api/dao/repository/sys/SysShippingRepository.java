package com.mouse.api.dao.repository.sys;

import com.mouse.api.dao.entity.sys.SysShippingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 快递公司
 * @Date 2019-11-26
 */
@Repository
public interface SysShippingRepository extends JpaRepository<SysShippingEntity, Integer>, JpaSpecificationExecutor<SysShippingEntity> {
}
