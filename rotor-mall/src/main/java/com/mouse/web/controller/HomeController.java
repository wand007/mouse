package com.mouse.web.controller;

import com.mouse.api.feign.HomeFeign;
import com.mouse.core.base.R;
import com.mouse.core.params.RotorSessionUser;
import com.mouse.web.base.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author ; lidongdong
 * @Description 首页服务
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("home")
public class HomeController extends GlobalExceptionHandler {
    @Autowired
    HomeFeign homeFeign;

    /**
     * 首页数据
     * 当用户已经登录时，非空。为登录状态为null
     *
     * @return
     */
    @ResponseBody
    @GetMapping(value = "index")
    public R index(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser) {
        return homeFeign.index(sessionUser.getId());
    }

    /**
     * 商城介绍信息
     *
     * @return 商城介绍信息
     */
    @GetMapping("/about")
    public R about() {
        return homeFeign.about();
    }
}

