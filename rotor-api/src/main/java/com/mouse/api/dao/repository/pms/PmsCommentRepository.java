package com.mouse.api.dao.repository.pms;

import com.mouse.api.dao.entity.pms.PmsCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 商品评价表
 * @Date 2019-11-26
 */
@Repository
public interface PmsCommentRepository extends JpaRepository<PmsCommentEntity, Long>, JpaSpecificationExecutor<PmsCommentEntity> {

}