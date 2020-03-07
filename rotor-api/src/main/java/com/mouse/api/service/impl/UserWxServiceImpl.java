package com.mouse.api.service.impl;

import com.mouse.api.service.UserWxService;
import com.mouse.dao.entity.user.UserFormIdEntity;
import com.mouse.dao.entity.user.UserWXH5Entity;
import com.mouse.dao.repository.user.UserFormIdRepository;
import com.mouse.dao.repository.user.UserWXH5Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2020-02-21
 */
@Slf4j
@Service
public class UserWxServiceImpl implements UserWxService {
    @Autowired
    UserFormIdRepository userFormIdRepository;
    @Autowired
    UserWXH5Repository userWXH5Repository;

    @Override
    public Optional<UserFormIdEntity> findUserFormIdById(String id) {
        return userFormIdRepository.findById(id);
    }

    @Override
    public Optional<UserWXH5Entity> findUserWXH5ById(String id) {
        return userWXH5Repository.findByUserIdAndDeleted(id, false);
    }
}
