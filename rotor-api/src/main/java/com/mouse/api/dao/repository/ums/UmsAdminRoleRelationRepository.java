package com.mouse.api.dao.repository.ums;

import com.mouse.api.dao.entity.ums.UmsAdminRoleRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 后台用户和角色关系表
 * @Date 2019-11-26
 */
@Repository
public interface UmsAdminRoleRelationRepository extends JpaRepository<UmsAdminRoleRelationEntity, Long>, JpaSpecificationExecutor<UmsAdminRoleRelationEntity> {

}