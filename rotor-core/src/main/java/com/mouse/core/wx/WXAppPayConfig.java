package com.mouse.core.wx;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-26
 */
public class WXAppPayConfig {

    private String appID;
    private String mchID;
    private String key;
    private String certpath;
    private String notifyUrl;


    public WXAppPayConfig(String appID, String mchID, String key, String certpath, String notifyUrl) {
        this.appID = appID;
        this.mchID = mchID;
        this.key = key;
        this.certpath = certpath;
        this.notifyUrl = notifyUrl;
    }
}
