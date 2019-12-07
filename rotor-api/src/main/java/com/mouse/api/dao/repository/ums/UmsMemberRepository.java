package com.mouse.api.dao.repository.ums;

import com.mouse.api.dao.entity.ums.UmsMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 会员表
 * @Date 2019-11-26
 */
@Repository
public interface UmsMemberRepository extends JpaRepository<UmsMemberEntity, Long>, JpaSpecificationExecutor<UmsMemberEntity> {

}