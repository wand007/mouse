package com.mouse.api.dao.repository.sys;

import com.mouse.api.dao.entity.sys.SysRegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 省市县区域
 * @Date 2019-11-26
 */
@Repository
public interface SysRegionRepository extends JpaRepository<SysRegionEntity, Integer>, JpaSpecificationExecutor<SysRegionEntity> {

}
