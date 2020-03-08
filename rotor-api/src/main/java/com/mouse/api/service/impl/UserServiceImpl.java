package com.mouse.api.service.impl;

import com.mouse.api.service.UserService;
import com.mouse.core.utils.SnowflakeIdWorker;
import com.mouse.dao.entity.user.UserEntity;
import com.mouse.dao.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
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
    SnowflakeIdWorker snowflakeIdWorker;
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

    @Override
    public Optional<UserEntity> findTopByMobile(String mobile) {
        return userRepository.findTopByMobileOrderByIdDesc(mobile);
    }

    @Override
    public UserEntity save(String username, String password, String mobile, String lastLoginIp) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(snowflakeIdWorker.nextId());
        userEntity.setUserName(username);
        userEntity.setPassword(encodedPassword);
        userEntity.setMobile(mobile);
        userEntity.setWeixinOpenid(null);
        userEntity.setAvatar("https://yanxuan.nosdn.127.net/80841d741d7fa3073e0ae27bf487339f.jpg?imageView&quality=90&thumbnail=64x64");
        userEntity.setNickName(username);
        userEntity.setGender(0);
        userEntity.setUserLevel(0);
        userEntity.setStatus(0);
        userEntity.setLastLoginTime(LocalDateTime.now());
        userEntity.setLastLoginIp(lastLoginIp);
        userEntity.setDeleted(false);
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public Integer updateLastLogin(String id,  String landingIP) {
        return userRepository.updateLastLogin(id,landingIP);
    }
}
