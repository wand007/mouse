package com.mouse.api.dao.repository.sys;

import com.mouse.api.dao.entity.sys.SysMacroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 通用字典表实体
 * @Date 2019-11-26
 */
@Repository
public interface SysMacroRepository extends JpaRepository<SysMacroEntity, Integer>, JpaSpecificationExecutor<SysMacroEntity> {
}
