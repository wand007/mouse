package com.mouse.api.event;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 发送邮件事件
 * @Date 2019-12-17
 */
@Getter
@Setter
@Builder(toBuilder = true)
public class EMailEvent implements Serializable {
    private static final long serialVersionUID = -7324680032019686260L;
    /**
     * 邮件标识
     */
    private String mailKey;

    /**
     * 标题
     */
    private String mailTitle;

    /**
     * 地址
     */
    private String[] emailAddr;

    /**
     * 内容
     */
    private String mailContent;

    /**
     * 附件
     */
    private File[] files;

    /**
     * 间隔时间
     */
    private Integer timeIntervalMinutes = 30;


}
