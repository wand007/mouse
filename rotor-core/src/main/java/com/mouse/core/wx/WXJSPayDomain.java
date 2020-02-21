package com.mouse.core.wx;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-26
 */
public class WXJSPayDomain implements IWXPayDomain{
    @Override
    public void report(String domain, long elapsedTimeMillis, Exception ex) {

    }

    @Override
    public DomainInfo getDomain(WXPayConfig config) {
        return null;
    }
}
