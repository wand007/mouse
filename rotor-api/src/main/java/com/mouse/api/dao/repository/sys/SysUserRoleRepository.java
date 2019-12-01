package com.mouse.api.dao.repository.sys;


import com.mouse.api.dao.entity.sys.SysUserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 用户与角色对应关系
 * @Date 2019-11-26
 */
@Repository
public interface SysUserRoleRepository extends JpaRepository<SysUserRoleEntity, Integer>, JpaSpecificationExecutor<SysUserRoleEntity> {
}
