package com.mouse.api.dao.repository.ums;

import com.mouse.api.dao.entity.ums.UmsPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 后台用户权限表
 * @Date 2019-11-26
 */
@Repository
public interface UmsPermissionRepository extends JpaRepository<UmsPermissionEntity, Long>, JpaSpecificationExecutor<UmsPermissionEntity> {

}