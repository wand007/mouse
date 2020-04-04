package com.mouse.api.client.mall;

import com.mouse.api.base.GlobalExceptionHandler;
import com.mouse.api.commons.AuthComm;
import com.mouse.api.commons.enums.RefererEnum;
import com.mouse.api.commons.req.wx.WxLoginInfo;
import com.mouse.api.feign.mall.AuthFeign;
import com.mouse.api.service.CouponUserService;
import com.mouse.api.service.UserService;
import com.mouse.core.base.BusinessCode;
import com.mouse.core.base.BusinessException;
import com.mouse.core.base.R;
import com.mouse.core.params.req.UserInfo;
import com.mouse.dao.entity.user.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * @author ; lidongdong
 * @Description 鉴权服务 API
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("auth")
public class AuthClient extends GlobalExceptionHandler implements AuthFeign {

    @Autowired
    AuthComm authComm;
    @Autowired
    UserService userService;
    @Autowired
    CouponUserService couponUserService;

    @Override
    public R login(String username, String password, RefererEnum referer, String userAgent, String landingIP) {

        UserEntity userEntity = userService.findTopByUserName(username).orElseThrow(() -> new BusinessException("账号不存在"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, userEntity.getPassword())) {
            return R.error("账号密码不对");
        }
        String token = UUID.randomUUID().toString();
        authComm.asyncRefreshCacheLogin(userEntity.getId(), referer, userAgent, token, landingIP);

        // userInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(username);
        userInfo.setAvatarUrl(userEntity.getAvatar());
        Map<String, Object> result = new HashMap<>(8);
        result.put("token", token);
        result.put("userInfo", userInfo);
        return R.success(result);
    }

    @Override
    public R logout(Integer userId) {
        authComm.removeCacheLogin(userId);
        return R.success();
    }

    @Override
    public R loginByWeiXin(WxLoginInfo wxLoginInfo) {
        return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
    }

    @Override
    public R registerCaptcha(String mobile) {
        return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
    }

    @Override
    public R register(String username, String password, String mobile, RefererEnum referer, String userAgent, String lastLoginIp, String code, String wxCode) {

        Optional<UserEntity> userEntityOptional = userService.findTopByUserName(username);
        if (userEntityOptional.isPresent()) {
            return R.error("用户名已注册");
        }
        userEntityOptional = userService.findTopByMobile(mobile);
        if (userEntityOptional.isPresent()) {
            return R.error("手机号已注册");
        }
        UserEntity userEntity = userService.save(username, password, mobile, lastLoginIp);
        String token = UUID.randomUUID().toString();
        authComm.asyncRefreshCacheLogin(userEntity.getId(), referer, userAgent, token, lastLoginIp);

        // userInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(username);
        userInfo.setAvatarUrl(userEntity.getAvatar());
        Map<String, Object> result = new HashMap<>(8);
        result.put("token", token);
        result.put("userInfo", userInfo);
        return R.success(result);
    }

    @Override
    public R captcha(Integer userId, String type, String mobile) {
        return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
    }

    @Override
    public R reset(Integer userId, String password, String mobile, String code) {
        return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
    }

    @Override
    public R resetPhone(Integer userId, String password, String mobile, String code) {
        return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
    }

    @Override
    public R profile(Integer userId, String password, String mobile, String code) {
        return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
    }

    @Override
    public R bindPhone(Integer userId, String encryptedData, String iv) {
        return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
    }


    @Override
    public R info(Integer userId) {
        return R.fromBusinessCode(BusinessCode.ERROR_SYS_SERVICE_RESTART);
    }
}
