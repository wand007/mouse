package com.mouse.web.controller;

import com.mouse.api.feign.SearchFeign;
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
 * @Description 商品搜索服务
 * @Date 2019-12-15
 */

@Slf4j
@Validated
@RestController
@RequestMapping("search")
public class SearchController extends GlobalExceptionHandler {
    @Autowired
    SearchFeign searchFeign;

    /**
     * 搜索页面信息
     * <p>
     * 如果用户已登录，则给出用户历史搜索记录；
     * 如果没有登录，则给出空历史搜索记录。
     *
     * @return 搜索页面信息
     */
    @GetMapping("index")
    public R index(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser) {
        return searchFeign.index(sessionUser.getId());
    }

    /**
     * 关键字提醒
     * <p>
     * 当用户输入关键字一部分时，可以推荐系统中合适的关键字。
     *
     * @param keyword 关键字
     * @return 合适的关键字
     */
    @GetMapping("helper")
    public R helper(@RequestParam(name = "keyword") String keyword,
                    @Min(value = 0, message = "必须从0页开始")
                    @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                    @Min(value = 1, message = "每页必须大于1")
                    @Max(value = 300, message = "每页必须小于300")
                    @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        return searchFeign.helper(keyword, pageNum, pageSize);
    }

    /**
     * 清除用户搜索历史
     *
     * @return 清理是否成功
     */
    @PostMapping("clearhistory")
    public R clearhistory(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser) {
        return searchFeign.clearhistory(sessionUser.getId());
    }
}

