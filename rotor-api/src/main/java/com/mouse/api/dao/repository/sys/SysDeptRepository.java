package com.mouse.api.dao.repository.sys;

import com.mouse.api.dao.entity.sys.SysDeptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 部门管理
 * @Date 2019-11-26
 */
@Repository
public interface SysDeptRepository extends JpaRepository<SysDeptEntity, Integer>, JpaSpecificationExecutor<SysDeptEntity> {
}
