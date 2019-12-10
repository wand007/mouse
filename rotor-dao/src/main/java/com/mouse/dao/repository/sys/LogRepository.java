package com.mouse.dao.repository.sys;

import com.mouse.dao.entity.sys.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface LogRepository extends JpaRepository<LogEntity, Integer>, JpaSpecificationExecutor<LogEntity> {

}