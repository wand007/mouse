package com.mouse.api.client;

import com.mouse.api.base.GlobalExceptionHandler;
import com.mouse.api.commons.req.SaveAftersaleReq;
import com.mouse.api.feign.AftersaleFeign;
import com.mouse.api.service.AftersaleService;
import com.mouse.core.base.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 售后服务 API
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("aftersale")
public class AftersaleClient extends GlobalExceptionHandler implements AftersaleFeign {

    @Autowired
    AftersaleService aftersaleService;

    @Override
    public R findPage(String userId, Integer status, @Min(value = 0, message = "必须从0页开始") Integer pageNum, @Min(value = 1, message = "每页必须大于1") @Max(value = 300, message = "每页必须小于300") Integer pageSize) {
        return null;
    }

    @Override
    public R detail(String userId, Integer orderId) {
        return null;
    }

    @Override
    public R submit(String userId, SaveAftersaleReq aftersale) {
        return null;
    }

    @Override
    public R cancel(String userId, Integer id) {
        return null;
    }
}
