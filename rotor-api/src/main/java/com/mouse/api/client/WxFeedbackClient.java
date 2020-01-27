package com.mouse.api.client;

import com.mouse.api.base.BaseClient;
import com.mouse.api.commons.req.FeedbackReq;
import com.mouse.api.feign.FeedbackFeign;
import com.mouse.api.service.FeedbackService;
import com.mouse.api.service.UserService;
import com.mouse.core.base.BusinessException;
import com.mouse.core.base.R;
import com.mouse.dao.entity.user.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ; lidongdong
 * @Description 意见反馈服务
 * @Date 2020-01-25
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/wx/feedback")
public class WxFeedbackClient extends BaseClient implements FeedbackFeign {
    @Autowired
    UserService userService;
    @Autowired
    FeedbackService feedbackService;


    @Override
    public R submit(@RequestParam(defaultValue = "userId") Integer userId,
                    @RequestBody FeedbackReq param) {
        UserEntity userEntity = userService.findById(userId).orElseThrow(() -> new BusinessException("用户记录不存在"));

        param.setUserId(userId);
        param.setUserName(userEntity.getUserName());
        feedbackService.save(param);
        return R.success();
    }
}
