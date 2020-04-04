package com.mouse.web.controller;

import com.mouse.api.commons.req.FeedbackReq;
import com.mouse.api.feign.mall.FeedbackFeign;
import com.mouse.core.base.R;
import com.mouse.core.params.RotorSessionUser;
import com.mouse.web.base.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author ; lidongdong
 * @Description 意见反馈服务
 * @Date 2020-01-25
 */

@Slf4j
@Validated
@RestController
@RequestMapping("feedback")
public class FeedbackController extends GlobalExceptionHandler {
    @Autowired
    FeedbackFeign feedbackFeign;

    /**
     * 添加意见反馈
     *
     * @param param 意见反馈
     * @return 操作结果
     */
    @PostMapping("submit")
    public R submit(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestBody FeedbackReq param) {
        return feedbackFeign.submit(sessionUser.getId(), param);
    }
}
