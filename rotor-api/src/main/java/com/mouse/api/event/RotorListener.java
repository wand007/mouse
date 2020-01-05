package com.mouse.api.event;

import com.mouse.core.config.RotorConfig;
import com.mouse.core.message.EmailTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author ; lidongdong
 * @Description 消息事件监听
 * @Date 2019-12-17
 */
@Slf4j
@Component
public class RotorListener {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    EmailTool emailTool;

    @Async
    @EventListener
    public void smsListener(SmsEvent event) {
        log.info("监听到SmsEvent事件源：" + event.getMobile());

    }

    @Async
    @EventListener
    public void emailListener(EMailEvent mailSendVO) {
        log.info("监听到EMailEvent事件源：" + mailSendVO.getMailTitle());
        String key = RotorConfig.UserPrefix.MAIL_SEND_FLAG.concat(mailSendVO.getMailKey());
        if (redisTemplate.hasKey(key)) {
            return;
        }
        if (null == mailSendVO.getFiles() || 0 == mailSendVO.getFiles().length) {
            emailTool.send(mailSendVO.getEmailAddr(), mailSendVO.getMailTitle(), mailSendVO.getMailContent());
        } else {
            emailTool.send(mailSendVO.getEmailAddr(), mailSendVO.getMailTitle(), mailSendVO.getMailContent(), mailSendVO.getFiles());
        }
        redisTemplate.opsForValue().set(key, mailSendVO.getMailTitle(), mailSendVO.getTimeIntervalMinutes(), TimeUnit.MINUTES);
    }
}
