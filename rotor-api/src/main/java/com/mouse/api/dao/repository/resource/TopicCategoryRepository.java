package com.mouse.api.dao.repository.resource;

import com.mouse.api.dao.entity.operate.TopicCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 活动类别
 * @Date 2019-11-26
 */
@Repository
public interface TopicCategoryRepository extends JpaRepository<TopicCategoryEntity, Integer>, JpaSpecificationExecutor<TopicCategoryEntity> {
}
