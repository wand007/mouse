package com.mouse.api.event;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 发送短信事件
 * @Date 2019-12-17
 */
@Getter
@Setter
@Builder(toBuilder = true)
public class SmsEvent implements Serializable {
    private static final long serialVersionUID = -6905229640671866949L;
    /**
     * 手机号
     */
    private String mobile;

    /**
     * 验证码
     */
    private String smsCode;

}
