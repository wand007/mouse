package com.mouse.dao.repository.user;

import com.mouse.dao.entity.user.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer>, JpaSpecificationExecutor<CommentEntity> {
}