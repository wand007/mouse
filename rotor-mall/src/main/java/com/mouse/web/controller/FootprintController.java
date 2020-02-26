package com.mouse.web.controller;

import com.mouse.api.feign.FootprintFeign;
import com.mouse.core.base.R;
import com.mouse.core.params.RotorSessionUser;
import com.mouse.web.base.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 用户访问足迹服务
 * @Date 2019-12-15
 */

@Slf4j
@Validated
@RestController
@RequestMapping("footprint")
public class FootprintController extends GlobalExceptionHandler {
    @Autowired
    FootprintFeign footprintFeign;

    /**
     * 删除用户足迹
     *
     * @param id 请求内容， { id: xxx }
     * @return 删除操作结果
     */
    @PostMapping("delete")
    public R delete(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestParam(defaultValue = "id") String id) {
        return footprintFeign.delete(sessionUser.getId(), id);
    }

    /**
     * 用户足迹列表
     *
     * @param pageNum  分页页数
     * @param pageSize 分页大小
     * @return 用户足迹列表
     */
    @GetMapping("findPage")
    public R findPage(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                      @Min(value = 0, message = "必须从0页开始")
                      @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                      @Min(value = 1, message = "每页必须大于1")
                      @Max(value = 300, message = "每页必须小于300")
                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        return footprintFeign.findPage(sessionUser.getId(), pageNum, pageSize);
    }
}

