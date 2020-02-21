package com.mouse.core.wx.param;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ; lidongdong
 * @Description 微信用户的信息
 * @Date 2019-12-31
 */
@Data
public class WxUserInfo implements Serializable {

    private static final long serialVersionUID = 741456553193599293L;
    /**
     * 用户的唯一标识
     */
    private String openid;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private String sex;

    /**
     * 用户个人资料填写的省份
     */
    private String province;

    /**
     * 普通用户个人资料填写的城市
     */
    private String city;

    /**
     * 国家，如中国为CN
     */
    private String country;

    /**
     * 国家，如中国为CN
     */
    private String headimgurl;

    /**
     * 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     */
    private List<String> privilege;

    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    private String unionid;
}
