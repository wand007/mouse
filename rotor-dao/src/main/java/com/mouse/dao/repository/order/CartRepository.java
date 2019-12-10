package com.mouse.dao.repository.order;

import com.mouse.dao.entity.order.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer>, JpaSpecificationExecutor<CartEntity> {
}