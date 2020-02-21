package com.mouse.dao.repository.order;

import com.mouse.dao.entity.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description 相册表
 * @Date 2019-11-26
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String>, JpaSpecificationExecutor<OrderEntity> {

    Optional<List<OrderEntity>> findByUserIdAndDeleted(Integer userId, Boolean deleted);

    /**
     * 修改订单终结状态
     *
     * @param id          订单ID
     * @param orderStatus 订单状态
     * @param endTime     结束时间
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class, timeout = 30, isolation = Isolation.REPEATABLE_READ)
    @Query(value = "update OrderEntity r set r.orderStatus = ?2,r.endTime = ?3, r.updateTime = now() where r.id = ?1")
    Integer updateStatusAndEndTime(String id, Short orderStatus, LocalDateTime endTime);
}