package com.mouse.api.dao.repository.oms;

import com.mouse.api.dao.entity.oms.OmsCartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 购物车表
 * @Date 2019-11-26
 */
@Repository
public interface OmsCartItemRepository extends JpaRepository<OmsCartItemEntity, Long>, JpaSpecificationExecutor<OmsCartItemEntity> {

}