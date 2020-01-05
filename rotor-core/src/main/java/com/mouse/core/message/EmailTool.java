package com.mouse.core.message;

import com.mouse.core.config.RotorConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @author ; lidongdong
 * @Description 邮件下发工具
 * @Date 2020-01-02
 */
@Component
public class EmailTool {

    private static final Logger logger = LoggerFactory.getLogger(EmailTool.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    public RedisTemplate redisTemplate;


    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";


    /**
     * 校验是否是邮箱
     *
     * @param email
     * @return
     */
    public static Boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 发送邮件方法
     *
     * @param emailAddr
     * @param mailTitle
     * @param mailContent
     * @param
     * @throws Exception
     */
    public void send(final String emailAddr, final String mailTitle, final String mailContent) {
        try {
            logger.info("send Mail To: " + emailAddr + " Mail Title: " + mailTitle + " mailConcept: " + mailContent);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("code@hvyogo.com");
            message.setTo(emailAddr);
            message.setSubject(MimeUtility.encodeWord(RotorConfig.SysPrefix.ENV_PRE + ":" + mailTitle, "UTF-8", "Q"));
            message.setText(mailContent);
            mailSender.send(message);
        } catch (Exception e) {
            logger.error("邮件发送异常:", e);
        }
    }

    /**
     * 发送邮件方法 -- 群发
     *
     * @param emailAddr
     * @param mailTitle
     * @param mailContent
     * @param
     * @throws Exception
     */
    public void send(final String[] emailAddr, final String mailTitle, final String mailContent) {
        try {
            logger.info("send Mail To: " + StringUtils.join(emailAddr, ",") + " Mail Title: " + mailTitle + " " +
                    "mailConcept: " + mailContent);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("code@hvyogo.com");
            message.setTo(emailAddr);
            message.setSubject(MimeUtility.encodeWord(RotorConfig.SysPrefix.ENV_PRE + ":" + mailTitle, "UTF-8", "Q"));
            message.setText(mailContent);
            mailSender.send(message);
        } catch (Exception e) {
            logger.error("邮件发送异常:", e);
        }
    }

    /**
     * 发送邮件方法 -- 群发 带附件
     *
     * @param emailAddr
     * @param mailTitle
     * @param mailContent
     * @param files       文件名加 文件对象
     * @throws Exception
     */
    public void send(final String[] emailAddr, final String mailTitle, final String mailContent, File[] files) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("code@hvyogo.com");
            helper.setTo(emailAddr);
            helper.setSubject(MimeUtility.encodeWord(RotorConfig.SysPrefix.ENV_PRE + ":" + mailTitle, "UTF-8", "Q"));
            helper.setText(mailContent);
            for (File file : files) {
                helper.addAttachment(file.getName(), file);
            }
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            logger.error("邮件发送异常:", e);
        }
    }


    /**
     * 限制发送频率
     *
     * @param emailAddr
     * @param mailTitle
     * @param mailContent
     * @param redisKey
     * @param minutes
     */
    @Async
    public void asyncSend(final String[] emailAddr, final String mailTitle, final String mailContent, String redisKey, Integer minutes) {
        if (!redisTemplate.hasKey(redisKey)) {
            this.send(emailAddr, mailTitle, mailContent);
            redisTemplate.opsForValue().set(redisKey, "true", minutes, TimeUnit.MINUTES);
        }
    }
}
