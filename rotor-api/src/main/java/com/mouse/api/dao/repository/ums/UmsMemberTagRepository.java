package com.mouse.api.dao.repository.ums;

import com.mouse.api.dao.entity.ums.UmsMemberTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 用户标签表
 * @Date 2019-11-26
 */
@Repository
public interface UmsMemberTagRepository extends JpaRepository<UmsMemberTagEntity, Long>, JpaSpecificationExecutor<UmsMemberTagEntity> {

}