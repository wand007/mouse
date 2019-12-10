package com.mouse.dao.repository.sys;

import com.mouse.dao.entity.sys.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 产品评价回复表
 * @Date 2019-11-26
 */
@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer>, JpaSpecificationExecutor<PermissionEntity> {

}