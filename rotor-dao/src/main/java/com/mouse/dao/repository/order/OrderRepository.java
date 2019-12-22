package com.mouse.dao.repository.order;

import com.mouse.dao.entity.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description 相册表
 * @Date 2019-11-26
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer>, JpaSpecificationExecutor<OrderEntity> {

    Optional<List<OrderEntity>> findByUserIdAndDeleted(Integer userId, Boolean deleted);
}