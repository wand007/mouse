package com.mouse.web.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2020-01-10
 */

public class BaseController {

    @Autowired
    public RedisTemplate redisTemplate;

}
