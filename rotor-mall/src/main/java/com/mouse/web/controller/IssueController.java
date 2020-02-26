package com.mouse.web.controller;

import com.mouse.api.feign.IssueFeign;
import com.mouse.core.base.R;
import com.mouse.web.base.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 用户服务
 * @Date 2019-12-15
 */

@Slf4j
@Validated
@RestController
@RequestMapping("issue")
public class IssueController extends GlobalExceptionHandler {
    @Autowired
    IssueFeign issueFeign;

    /**
     * 帮助中心
     *
     * @param pageNum
     * @param pageSize
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("findPage")
    R findPage(@RequestParam(defaultValue = "question", required = false) String question,
               @Min(value = 0, message = "必须从0页开始")
               @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
               @Min(value = 1, message = "每页必须大于1")
               @Max(value = 300, message = "每页必须小于300")
               @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
               @RequestParam(defaultValue = "add_time", required = false) String sort,
               @RequestParam(defaultValue = "desc", required = false) String order) {
        return issueFeign.findPage(question, pageNum, pageSize, sort, order);
    }
}

