package com.mouse.api.dao.repository.ums;

import com.mouse.api.dao.entity.ums.UmsMemberRuleSettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 会员积分成长规则表
 * @Date 2019-11-26
 */
@Repository
public interface UmsMemberRuleSettingRepository extends JpaRepository<UmsMemberRuleSettingEntity, Long>, JpaSpecificationExecutor<UmsMemberRuleSettingEntity> {

}