package com.mouse.api.dao.repository.sys;


import com.mouse.api.dao.entity.sys.SysMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 系统菜单实体
 * @Date 2019-11-26
 */
@Repository
public interface SysMenuRepository extends JpaRepository<SysMenuEntity, Integer>, JpaSpecificationExecutor<SysMenuEntity> {
}
