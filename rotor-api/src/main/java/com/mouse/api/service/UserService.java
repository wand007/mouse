package com.mouse.api.service;

import com.mouse.dao.entity.user.UserEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
public interface UserService {
    Optional<UserEntity> findById(String userId);

    Optional<List<UserEntity>> findByIdIn(List<String> userIds);
}
