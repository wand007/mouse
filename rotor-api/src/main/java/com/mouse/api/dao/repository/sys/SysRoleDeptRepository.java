package com.mouse.api.dao.repository.sys;


import com.mouse.api.dao.entity.sys.SysRoleDeptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 角色与部门对应关系
 * @Date 2019-11-26
 */
@Repository
public interface SysRoleDeptRepository extends JpaRepository<SysRoleDeptEntity, Integer>, JpaSpecificationExecutor<SysRoleDeptEntity> {

}
