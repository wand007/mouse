package com.mouse.dao.repository.order;

import com.mouse.dao.entity.order.OrderGoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface OrderGoodsRepository extends JpaRepository<OrderGoodsEntity, Integer>, JpaSpecificationExecutor<OrderGoodsEntity> {

}