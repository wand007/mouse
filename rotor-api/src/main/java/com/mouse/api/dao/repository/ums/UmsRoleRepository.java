package com.mouse.api.dao.repository.ums;

import com.mouse.api.dao.entity.ums.UmsRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 后台用户角色表
 * @Date 2019-11-26
 */
@Repository
public interface UmsRoleRepository extends JpaRepository<UmsRoleEntity, Long>, JpaSpecificationExecutor<UmsRoleEntity> {
}