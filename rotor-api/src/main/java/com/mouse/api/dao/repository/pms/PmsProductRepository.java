package com.mouse.api.dao.repository.pms;

import com.mouse.api.dao.entity.pms.PmsProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 商品信息
 * @Date 2019-11-26
 */
@Repository
public interface PmsProductRepository extends JpaRepository<PmsProductEntity, Long>, JpaSpecificationExecutor<PmsProductEntity> {


}