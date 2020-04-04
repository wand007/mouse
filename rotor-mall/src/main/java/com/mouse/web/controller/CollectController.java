package com.mouse.web.controller;

import com.mouse.api.feign.mall.CollectFeign;
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
 * @Description 用户收藏服务
 * @Date 2020-01-12
 */

@Slf4j
@Validated
@RestController
@RequestMapping("collect")
public class CollectController extends GlobalExceptionHandler {
    @Autowired
    CollectFeign collectFeign;

    /**
     * 用户收藏列表
     *
     * @param type     类型，如果是0则是商品收藏，如果是1则是专题收藏
     * @param pageNum  分页页数
     * @param pageSize 分页大小
     * @return
     */
    @GetMapping("findPage")
    public R findPage(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                      @RequestParam(name = "type") Byte type,
                      @Min(value = 0, message = "必须从0页开始")
                      @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                      @Min(value = 1, message = "每页必须大于1")
                      @Max(value = 300, message = "每页必须小于300")
                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize) {
        return collectFeign.findPage(sessionUser.getId(), type, pageNum, pageSize);
    }

    /**
     * 用户收藏添加或删除
     * <p>
     * 如果商品没有收藏，则添加收藏；如果商品已经收藏，则删除收藏状态。
     *
     * @param type    收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID
     * @param valueId 如果type=0，则是商品ID；如果type=1，则是专题ID
     * @return 操作结果
     */
    @PostMapping("addOrDelete")
    public R addOrDelete(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                         @RequestParam(name = "type") Integer type,
                         @RequestParam(name = "valueId") Integer valueId) {
        return collectFeign.addOrDelete(sessionUser.getId(), type, valueId);
    }
}
