package com.mouse.dao.repository.operate;

import com.mouse.dao.entity.operate.GrouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface GrouponRepository extends JpaRepository<GrouponEntity, Integer>, JpaSpecificationExecutor<GrouponEntity> {

    Optional<List<GrouponEntity>> findByIdAndDeletedAndStatusIn(Integer grouponId, boolean deleted, List<Short> statuss);

    Integer countByIdAndDeletedAndStatusIn(Integer grouponId, boolean deleted, List<Short> statuss);

    Optional<GrouponEntity> findByOrderId(String orderId);
}