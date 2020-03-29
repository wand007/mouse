package com.mouse.api.service.impl;

import com.mouse.api.commons.req.FeedbackReq;
import com.mouse.api.service.FeedbackService;
import com.mouse.core.utils.JsonUtils;
import com.mouse.dao.entity.user.FeedbackEntity;
import com.mouse.dao.repository.user.FeedbackRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        feedbackEntity.setDeleted(false);
        feedbackEntity.setHasPicture(CollectionUtils.isEmpty(param.getPicUrls()));
        feedbackEntity.setPicUrls(JsonUtils.toJson(param.getPicUrls()));
        feedbackRepository.save(feedbackEntity);
    }
}
