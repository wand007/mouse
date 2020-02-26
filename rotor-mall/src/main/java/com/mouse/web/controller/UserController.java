package com.mouse.web.controller;

import com.mouse.api.feign.UserFeign;
import com.mouse.core.base.R;
import com.mouse.core.params.RotorSessionUser;
import com.mouse.web.base.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ; lidongdong
 * @Description 用户服务
 * @Date 2019-12-15
 */

@Slf4j
@Validated
@RestController
@RequestMapping("user")
public class UserController extends GlobalExceptionHandler {
    @Autowired
    UserFeign userFeign;

    /**
     * 用户个人页面数据
     * <p>
     * 目前是用户订单统计信息
     *
     * @return 用户个人页面数据
     */
    @GetMapping("index")
    public R index(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser) {
        return userFeign.index(sessionUser.getId());
    }
}

