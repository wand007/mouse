package com.mouse.api.dao.repository.sys;


import com.mouse.api.dao.entity.sys.SysRoleMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 角色与菜单对应关系
 * @Date 2019-11-26
 */
@Repository
public interface SysRoleMenuRepository extends JpaRepository<SysRoleMenuEntity, Integer>, JpaSpecificationExecutor<SysRoleMenuEntity> {

}
