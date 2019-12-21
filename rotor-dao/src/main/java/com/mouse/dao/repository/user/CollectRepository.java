package com.mouse.dao.repository.user;

import com.mouse.dao.entity.user.CollectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface CollectRepository extends JpaRepository<CollectEntity, Integer>, JpaSpecificationExecutor<CollectEntity> {

    Integer countByUserIdAndValueIdAndDeleted(Integer userId, Integer goodsId, Boolean deleted);
}