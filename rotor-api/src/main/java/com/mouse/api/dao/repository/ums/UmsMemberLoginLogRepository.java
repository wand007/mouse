package com.mouse.api.dao.repository.ums;

import com.mouse.api.dao.entity.ums.UmsMemberLoginLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 会员登录记录
 * @Date 2019-11-26
 */
@Repository
public interface UmsMemberLoginLogRepository extends JpaRepository<UmsMemberLoginLogEntity, Long>, JpaSpecificationExecutor<UmsMemberLoginLogEntity> {

}