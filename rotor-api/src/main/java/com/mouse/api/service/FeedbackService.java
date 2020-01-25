package com.mouse.api.service;

import com.mouse.api.commons.req.FeedbackReq;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2020-01-25
 */
public interface FeedbackService {
    /**
     * 保存意见反馈记录
     *
     * @param param
     */
    void save(FeedbackReq param);
}
