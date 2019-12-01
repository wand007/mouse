package com.mouse.api.dao.repository.sys;


import com.mouse.api.dao.entity.sys.SysConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 系统配置信息
 * @Date 2019-11-26
 */
@Repository
public interface SysConfigRepository extends JpaRepository<SysConfigEntity, Integer>, JpaSpecificationExecutor<SysConfigEntity> {
}
