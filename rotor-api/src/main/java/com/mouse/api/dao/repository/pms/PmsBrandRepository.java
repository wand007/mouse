package com.mouse.api.dao.repository.pms;

import com.mouse.api.dao.entity.pms.PmsBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 品牌表
 * @Date 2019-11-26
 */
@Repository
public interface PmsBrandRepository extends JpaRepository<PmsBrandEntity, Long>, JpaSpecificationExecutor<PmsBrandEntity> {

}