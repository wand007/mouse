package com.mouse.dao.repository.user;

import com.mouse.dao.entity.user.CollectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface CollectRepository extends JpaRepository<CollectEntity, Integer>, JpaSpecificationExecutor<CollectEntity> {

    Integer countByUserIdAndValueIdAndDeleted(String userId, Integer goodsId, Boolean deleted);

    /**
     * 查询用户收藏记录
     *
     * @param userId  用户ID
     * @param type    收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID
     * @param valueId 如果type=0，则是商品ID；如果type=1，则是专题ID
     * @return
     */
    Optional<CollectEntity> findByUserIdAndValueIdAndType(String userId, Integer valueId, Integer type);
}