package com.mouse.api.dao.repository.ums;

import com.mouse.api.dao.entity.ums.UmsMemberMemberTagRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 用户和标签关系表
 * @Date 2019-11-26
 */
@Repository
public interface UmsMemberMemberTagRelationRepository extends JpaRepository<UmsMemberMemberTagRelationEntity, Long>, JpaSpecificationExecutor<UmsMemberMemberTagRelationEntity> {
}