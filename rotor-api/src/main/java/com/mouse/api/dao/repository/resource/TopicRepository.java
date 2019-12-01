package com.mouse.api.dao.repository.resource;

import com.mouse.api.dao.entity.operate.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 活动
 * @Date 2019-11-26
 */
@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, Integer>, JpaSpecificationExecutor<TopicEntity> {
}
