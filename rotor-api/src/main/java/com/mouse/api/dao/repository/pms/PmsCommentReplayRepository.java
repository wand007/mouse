package com.mouse.api.dao.repository.pms;

import com.mouse.api.dao.entity.pms.PmsCommentReplayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 产品评价回复表
 * @Date 2019-11-26
 */
@Repository
public interface PmsCommentReplayRepository extends JpaRepository<PmsCommentReplayEntity, Long>, JpaSpecificationExecutor<PmsCommentReplayEntity> {

}