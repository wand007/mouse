package com.mouse.dao.repository.user;

import com.mouse.dao.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 根据用户名称查询用户记录
     *
     * @param username 用户名称
     * @return
     */
    Optional<UserEntity> findTopByUserNameOrderByIdDesc(String username);

    /**
     * 根据手机号查询用户记录
     *
     * @param mobile 用户手机号
     * @return
     */
    Optional<UserEntity> findTopByMobileOrderByIdDesc(String mobile);

    /**
     * 修改用户登录信息
     *
     * @param id        用户ID
     * @param landingIP 登录IP
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update UserEntity r set r.lastLoginIp = ?2, r.lastLoginTime = now(), r.updateTime = now() where r.id in ?1")
    Integer updateLastLogin(String id, String landingIP);
}