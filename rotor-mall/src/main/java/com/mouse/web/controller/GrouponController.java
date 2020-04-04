package com.mouse.web.controller;

import com.mouse.api.feign.mall.GrouponFeign;
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
 * @Description 团购服务
 * @Date 2019-12-15
 */

@Slf4j
@Validated
@RestController
@RequestMapping("groupon")
public class GrouponController extends GlobalExceptionHandler {
    @Autowired
    GrouponFeign grouponFeign;

    /**
     * 团购规则列表
     *
     * @param pageNum
     * @param pageSize
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("findPage")
    public R findPage(@Min(value = 0, message = "必须从0页开始")
                      @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                      @Min(value = 1, message = "每页必须大于1")
                      @Max(value = 300, message = "每页必须小于300")
                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                      @RequestParam(name = "sort", defaultValue = "add_time", required = false) String sort,
                      @RequestParam(name = "order", defaultValue = "desc", required = false) String order) {
        return grouponFeign.findPage(pageNum, pageSize, sort, order);
    }

    /**
     * 团购活动详情
     *
     * @param grouponId 团购活动ID
     * @return 团购活动详情
     */
    @GetMapping("detail")
    public R detail(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                    @RequestParam(name = "grouponId") Integer grouponId) {
        return grouponFeign.detail(sessionUser.getId(), grouponId);
    }

    /**
     * 参加团购
     *
     * @param grouponId 团购活动ID
     * @return 操作结果
     */
    @GetMapping("join")
    public R join(@RequestParam(name = "grouponId") Integer grouponId) {
        return grouponFeign.join(grouponId);
    }

    /**
     * 用户开团或入团情况
     *
     * @param showType 显示类型，如果是0，则是当前用户开的团购；否则，则是当前用户参加的团购
     * @return 用户开团或入团情况
     */
    @GetMapping("my")
    public R my(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                @RequestParam(name = "showType") Integer showType) {
        return grouponFeign.my(sessionUser.getId(), showType);
    }

}

