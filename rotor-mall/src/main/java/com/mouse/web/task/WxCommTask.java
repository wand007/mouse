package com.mouse.web.task;

import com.mouse.api.commons.enums.RefererEnum;
import com.mouse.core.config.RotorRedisConfig;
import com.mouse.core.utils.RedisLock;
import com.mouse.core.wx.WxH5Comm;
import com.mouse.web.base.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.mouse.core.config.RotorConfig.WxPrefix.*;

/**
 * @author ; lidongdong
 * @Description 微信相关 定时任务
 * @Date 2019-12-13
 */
@Slf4j
@Component
@EnableScheduling
public class WxCommTask extends GlobalExceptionHandler {

    @Autowired
    RedisLock redisLock;
    @Autowired
    WxH5Comm wxH5Comm;

    /**
     * 每1小时 定时刷新token
     */
    @Scheduled(cron = "0 0 *  * * ?")
    public void refeshToken() {

        String redisKey = RotorRedisConfig.LockPrefix.HVYOSV_TASK_REFESH_TOKEN_LOCK;
        RLock lock = redisLock.getLock(redisKey);
        try {
            lock.lock();
            log.info("定时任务:刷新token");
            if (redisTemplate.hasKey(TOKEN_IGNOE_FLAG_H5)) {
                log.info("定时任务:不刷新token");
                return;
            }
            String accessToken = wxH5Comm.refreshAccessToken();
            redisTemplate.opsForValue().set(WX_ACCESS_TOKEN + RefererEnum.WX.getCode(), accessToken);
            String jsApiToken = wxH5Comm.refreshJsApiToken(accessToken);
            redisTemplate.opsForValue().set(JS_API_TOKEN_REDIS_KEY + RefererEnum.WX.getCode(), jsApiToken);
        } finally {
            log.info("定时任务:刷新token------Ending");
            lock.unlock();
        }

    }
}
