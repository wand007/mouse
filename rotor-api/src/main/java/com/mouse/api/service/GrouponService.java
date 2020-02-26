package com.mouse.api.service;

import com.mouse.dao.entity.operate.GrouponEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-15
 */
public interface GrouponService {

    Optional<GrouponEntity> findById(Integer grouponId);

    Optional<List<GrouponEntity>> findByIdAndStatus(Integer grouponId);

    List<GrouponEntity> findByUserIdAndCreatorUserId(String userId);

    List<GrouponEntity> findByUserId(String userId);

    Integer countById(Integer grouponId);

    Optional<GrouponEntity> findByOrderId(String orderId);

    Long countByGrouponId(Integer grouponId);

    Long countByUserIdAndGrouponId(String userId, Integer grouponId);

    void createGroupon(GrouponEntity groupon);
}
