package com.mouse.api.commons;

import com.mouse.api.base.BaseClient;
import com.mouse.api.commons.enums.RefererEnum;
import com.mouse.api.service.UserService;
import com.mouse.api.service.UserWxService;
import com.mouse.core.base.BusinessException;
import com.mouse.core.config.RotorConfig;
import com.mouse.core.message.EmojiCharacterUtil;
import com.mouse.core.params.RotorSessionUser;
import com.mouse.dao.entity.user.UserEntity;
import com.mouse.dao.entity.user.UserWXH5Entity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2020-03-07
 */
@Slf4j
@Component
public class AuthComm extends BaseClient {
    @Autowired
    UserService userService;
    @Autowired
    UserWxService userWxService;

    /**
     * 更新用户登录缓存信息 --异步
     *
     * @param userId
     * @return
     */
    @Async
    public String asyncRefreshCacheLogin(String userId, RefererEnum referer, String userAgent, String landingIP) {
        try {
            return this.refreshCacheLogin(userId, referer, userAgent, landingIP);
        } catch (Exception e) {
            log.error("异步更新用户登录缓存信息异常,userId:" + userId, e);
            throw new BusinessException("异步更新用户登录缓存信息异常");
        }
    }

    /**
     * 更新用户登录缓存信息
     * 没有直接通过token对应缓存的登陆信息，是因为如果后台需要强制用户下线的话无法通过userId反向找到对应的token
     * 而通过token对应userId，userId在对应缓存的登陆信息，则可以通过userId来直接清除掉登陆信息来达到强制下线的目的
     *
     * @param userId
     * @return
     */
    private String refreshCacheLogin(String userId, RefererEnum referer, String userAgent, String landingIP) {
        UserEntity userEntity = userService.findById(userId).orElseThrow(() -> new BusinessException("用户信息不存在"));

        String token = UUID.randomUUID().toString();
        RotorSessionUser rotorSessionUser = new RotorSessionUser();
        rotorSessionUser.setId(userEntity.getId());
        rotorSessionUser.setMobile(userEntity.getMobile());
        rotorSessionUser.setNickName(userEntity.getNickName());
        rotorSessionUser.setAvatarUrl(userEntity.getAvatar());
        rotorSessionUser.setReferer(referer.getCode());
        rotorSessionUser.setUserAgent(userAgent);
        rotorSessionUser.setH5Token(token);
        rotorSessionUser.setNeedAuthFlag(false);
        Optional<UserWXH5Entity> wxh5EntityOptional = userWxService.findUserWXH5ById(userEntity.getId());
        if (wxh5EntityOptional.isPresent()) {
            UserWXH5Entity userWXH5Entity = wxh5EntityOptional.get();
            rotorSessionUser.setNeedAuthFlag(true);
            rotorSessionUser.setOpenId(userWXH5Entity.getOpenId());
            rotorSessionUser.setUnionId(userWXH5Entity.getUnionId());
            rotorSessionUser.setNickName(EmojiCharacterUtil.reverse(userWXH5Entity.getNickName()));
        }
        //缓存用户token
        redisTemplate.opsForValue().set(RotorConfig.LoginPrefix.REDIS_TOKEN_AUTH + token, userEntity.getId(),
                RotorConfig.LoginPrefix.REDIS_TOKEN_AUTH_EXPIRED_DEFAUT, TimeUnit.DAYS);
        //缓存用户登陆信息
        redisTemplate.opsForValue().set(RotorConfig.LoginPrefix.REDIS_TOKEN_USER_INFO + userEntity.getId(),
                rotorSessionUser, RotorConfig.LoginPrefix.REDIS_TOKEN_AUTH_EXPIRED_DEFAUT, TimeUnit.DAYS);
        return token;
    }

    public void removeCacheLogin(Integer userId) {
        RotorSessionUser hvyosvSessionUser = (RotorSessionUser) redisTemplate.opsForValue().get(RotorConfig.LoginPrefix.REDIS_TOKEN_USER_INFO + userId);
        if (hvyosvSessionUser == null) {
            return;
        }
        redisTemplate.delete(RotorConfig.LoginPrefix.REDIS_TOKEN_AUTH + hvyosvSessionUser.getH5Token());
        redisTemplate.delete(RotorConfig.LoginPrefix.REDIS_TOKEN_USER_INFO + hvyosvSessionUser.getId());
    }
}
