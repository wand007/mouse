package com.mouse.api.dao.repository.sms;

import com.mouse.api.dao.entity.sms.SmsHomeRecommendSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 首页推荐专题表
 * @Date 2019-11-26
 */
@Repository
public interface SmsHomeRecommendSubjectRepository extends JpaRepository<SmsHomeRecommendSubjectEntity, Long>, JpaSpecificationExecutor<SmsHomeRecommendSubjectEntity> {
}