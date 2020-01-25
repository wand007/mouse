package com.mouse.api.service.impl;

import com.mouse.api.commons.req.FeedbackReq;
import com.mouse.api.service.FeedbackService;
import com.mouse.dao.entity.user.FeedbackEntity;
import com.mouse.dao.repository.user.FeedbackRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2020-01-25
 */
@Slf4j
@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;


    @Override
    public void save(FeedbackReq param) {
        FeedbackEntity feedbackEntity = new FeedbackEntity();
        feedbackEntity.setUserId(param.getUserId());
        feedbackEntity.setUserName(param.getUserName());
        feedbackEntity.setMobile(param.getMobile());
        feedbackEntity.setFeedType(param.getFeedType());
        feedbackEntity.setContent(param.getContent());
        feedbackEntity.setStatus(1);
        feedbackEntity.setHasPicture(StringUtils.isNotBlank(param.getPicUrls()));
        feedbackEntity.setPicUrls(param.getPicUrls());
        feedbackRepository.save(feedbackEntity);
    }
}
