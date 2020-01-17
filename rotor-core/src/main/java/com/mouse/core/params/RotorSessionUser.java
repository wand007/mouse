package com.mouse.core.params;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 用户登陆缓存信息
 * @Date 2019-12-18
 */
@Data
public class RotorSessionUser implements Serializable {

    private static final long serialVersionUID = 2803040840589339043L;
    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户手机号码
     */
    private String mobile;

    /**
     * 用户昵称或网络名称
     */
    private String nickName;

    /**
     * 头像
     */
    public String avatarUrl;

    /**
     * 登陆来源
     */
    private Integer referer;

    /**
     * 代理浏览器类型
     */
    private String userAgent;
    /**
     * h5登陆token
     */
    private String h5Token;

    /**
     * 平台openID
     */
    public String openId;

    /**
     * 平台 unionId
     */
    public String unionId;

    /**
     * 是否需要授权Flag
     */
    public Boolean needAuthFlag;

}
