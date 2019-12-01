package com.mouse.api.dao.repository.operate;

import com.mouse.api.dao.entity.operate.ChannelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface ChannelRepository extends JpaRepository<ChannelEntity, Integer>, JpaSpecificationExecutor<ChannelEntity> {
}
