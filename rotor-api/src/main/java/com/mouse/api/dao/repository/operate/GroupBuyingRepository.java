package com.mouse.api.dao.repository.operate;

import com.mouse.api.dao.entity.operate.GroupBuyingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 拼团
 * @Date 2019-11-26
 */
@Repository
public interface GroupBuyingRepository extends JpaRepository<GroupBuyingEntity, String>, JpaSpecificationExecutor<GroupBuyingEntity> {
}
