package com.mouse.api.service;

import com.mouse.dao.entity.user.UserFormIdEntity;
import com.mouse.dao.entity.user.UserWXH5Entity;

import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2020-02-21
 */
public interface UserWxService {
    Optional<UserFormIdEntity> findUserFormIdById(String id);

    Optional<UserWXH5Entity> findUserWXH5ById(String id);
}
