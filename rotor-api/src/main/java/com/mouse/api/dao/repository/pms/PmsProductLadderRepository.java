package com.mouse.api.dao.repository.pms;

import com.mouse.api.dao.entity.pms.PmsProductLadderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 产品阶梯价格表(只针对同商品)
 * @Date 2019-11-26
 */
@Repository
public interface PmsProductLadderRepository extends JpaRepository<PmsProductLadderEntity, Long>, JpaSpecificationExecutor<PmsProductLadderEntity> {

}