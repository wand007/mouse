package com.mouse.api.dao.repository.ums;

import com.mouse.api.dao.entity.ums.UmsRolePermissionRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 后台用户角色和权限关系表
 * @Date 2019-11-26
 */
@Repository
public interface UmsRolePermissionRelationRepository extends JpaRepository<UmsRolePermissionRelationEntity, Long>, JpaSpecificationExecutor<UmsRolePermissionRelationEntity> {

}