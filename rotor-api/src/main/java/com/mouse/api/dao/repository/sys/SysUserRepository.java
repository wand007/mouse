package com.mouse.api.dao.repository.sys;

import com.mouse.api.dao.entity.sys.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 系统用户
 * @Date 2019-11-26
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUserEntity, String>, JpaSpecificationExecutor<SysUserEntity> {

}
