package com.mouse.dao.repository.user;

import com.mouse.dao.entity.user.UserWXH5Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-11
 */
@Repository
public interface UserWXH5Repository extends JpaRepository<UserWXH5Entity, String>, JpaSpecificationExecutor<UserWXH5Entity> {

    /**
     * 根据用户ID查询为删除的微信h5用户记录
     *
     * @param userId
     * @param deleted
     * @return
     */
    Optional<UserWXH5Entity> findByUserIdAndDeleted(String userId, boolean deleted);
}