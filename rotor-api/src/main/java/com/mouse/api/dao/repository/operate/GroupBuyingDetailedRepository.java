package com.mouse.api.dao.repository.operate;

import com.mouse.api.dao.entity.operate.GroupBuyingDetailedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 拼团详情
 * @Date 2019-11-26
 */
@Repository
public interface GroupBuyingDetailedRepository extends JpaRepository<GroupBuyingDetailedEntity, String>, JpaSpecificationExecutor<GroupBuyingDetailedEntity> {
}
