package com.mouse.api.dao.repository.ums;

import com.mouse.api.dao.entity.ums.UmsAdminPermissionRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 后台用户和权限关系表(除角色中定义的权限以外的加减权限)
 * @Date 2019-11-26
 */
@Repository
public interface UmsAdminPermissionRelationRepository extends JpaRepository<UmsAdminPermissionRelationEntity, Long>, JpaSpecificationExecutor<UmsAdminPermissionRelationEntity> {

}