package com.mouse.api.service.impl;

import com.mouse.api.service.UserService;
import com.mouse.dao.entity.user.UserEntity;
import com.mouse.dao.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<UserEntity> findById(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<List<UserEntity>> findByIdIn(List<String> userIds) {
        return userRepository.findByIdIn(userIds);
    }

    @Override
    public Optional<UserEntity> findTopByUserName(String username) {
        return userRepository.findTopByUserNameOrderByIdDesc(username);
    }
}
