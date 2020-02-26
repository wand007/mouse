package com.mouse.web.controller;

import com.mouse.api.feign.TopicFeign;
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
 * @Description 专题服务
 * @Date 2019-12-15
 */

@Slf4j
@Validated
@RestController
@RequestMapping("topic")
public class TopicController extends GlobalExceptionHandler {
    @Autowired
    TopicFeign topicFeign;

    /**
     * 专题列表
     *
     * @param pageNum
     * @param pageSize
     * @param sort
     * @param order
     * @return 专题列表
     */
    @GetMapping("findPage")
    public R findPage(@Min(value = 0, message = "必须从0页开始")
                      @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                      @Min(value = 1, message = "每页必须大于1")
                      @Max(value = 300, message = "每页必须小于300")
                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                      @RequestParam(defaultValue = "add_time", required = false) String sort,
                      @RequestParam(defaultValue = "desc", required = false) String order) {
        return topicFeign.findPage(pageNum, pageSize, sort, order);
    }

    /**
     * 专题详情
     *
     * @param id 专题ID
     * @return 专题详情
     */
    @GetMapping("detail")
    public R detail(@RequestParam(defaultValue = "id") Integer id) {
        return topicFeign.detail(id);
    }

    /**
     * 相关专题
     *
     * @param id 专题ID
     * @return 相关专题
     */
    @GetMapping("related")
    public R related(@RequestParam(defaultValue = "id") Integer id) {
        return topicFeign.related(id);
    }
}

