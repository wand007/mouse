package com.mouse.api.dao.repository.cms;

import com.mouse.api.dao.entity.cms.CmsTopicCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 专题评论表
 * @Date 2019-11-26
 */
@Repository
public interface CmsTopicCommentRepository extends JpaRepository<CmsTopicCommentEntity, Long>, JpaSpecificationExecutor<CmsTopicCommentEntity> {

}