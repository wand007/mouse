package com.mouse.dao.repository.user;

import com.mouse.dao.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {

    /**
     * 查询用户集合
     *
     * @param userIds 用户ID集合
     * @return
     */
    Optional<List<UserEntity>> findByIdIn(List<String> userIds);
}