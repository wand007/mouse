package com.mouse.api.dao.repository.pms;

import com.mouse.api.dao.entity.pms.PmsProductAttributeValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 存储产品参数信息的表
 * @Date 2019-11-26
 */
@Repository
public interface PmsProductAttributeValueRepository extends JpaRepository<PmsProductAttributeValueEntity, Long>, JpaSpecificationExecutor<PmsProductAttributeValueEntity> {

}