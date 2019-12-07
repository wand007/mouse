package com.mouse.api.dao.repository.ums;

import com.mouse.api.dao.entity.ums.UmsMemberProductCategoryRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 会员与产品分类关系表（用户喜欢的分类）
 * @Date 2019-11-26
 */
@Repository
public interface UmsMemberProductCategoryRelationRepository extends JpaRepository<UmsMemberProductCategoryRelationEntity, Long>, JpaSpecificationExecutor<UmsMemberProductCategoryRelationEntity> {

}