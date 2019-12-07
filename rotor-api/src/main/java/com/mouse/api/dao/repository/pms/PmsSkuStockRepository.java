package com.mouse.api.dao.repository.pms;

import com.mouse.api.dao.entity.pms.PmsSkuStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description sku的库存
 * @Date 2019-11-26
 */
@Repository
public interface PmsSkuStockRepository extends JpaRepository<PmsSkuStockEntity, Long>, JpaSpecificationExecutor<PmsSkuStockEntity> {


}