package com.mouse.api.dao.repository.sys;


import com.mouse.api.dao.entity.sys.SysRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 角色
 * @Date 2019-11-26
 */
//@Repository
public interface SysRoleRepository extends JpaRepository<SysRoleEntity, Integer>, JpaSpecificationExecutor<SysRoleEntity> {
}
