package com.mouse.core.wx;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-26
 */
public class WXJSPayConfig extends WXPayConfig {

    private String appID;
    private String mchID;
    private String key;
    private String spbillCreateIp;
    private String certPath;
    private String notifyUrl;

    public WXJSPayConfig(String appID, String mchID, String key, String spbillCreateIp, String certPath, String notifyUrl) {
        this.appID = appID;
        this.mchID = mchID;
        this.key = key;
        this.spbillCreateIp = spbillCreateIp;
        this.certPath = certPath;
        this.notifyUrl = notifyUrl;
    }

    @Override
    String getAppID() {
        return this.appID;
    }

    @Override
    String getMchID() {
        return this.mchID;
    }

    @Override
    String getKey() {
        return this.key;
    }

    @Override
    InputStream getCertStream() {
        if (StringUtils.isBlank(certPath)) {
            return null;
        }
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(certPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
    }
}
