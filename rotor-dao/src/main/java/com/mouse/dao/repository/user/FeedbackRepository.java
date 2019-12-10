package com.mouse.dao.repository.user;

import com.mouse.dao.entity.user.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Integer>, JpaSpecificationExecutor<FeedbackEntity> {

}