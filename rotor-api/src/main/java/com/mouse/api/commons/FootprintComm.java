package com.mouse.api.commons;

import com.mouse.api.service.FootprintService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-15
 */
@Slf4j
@Component
public class FootprintComm {
    @Autowired
    FootprintService footprintService;

    /**
     * 异步保存用户足迹操作
     *
     * @param userId  用户ID
     * @param goodsId 商品ID
     */
    @Async
    public void asyncSave(String userId, Integer goodsId) {
        try {
            footprintService.save(userId, goodsId);
        } catch (Exception e) {
            log.error("异步保存用户足迹操作异常", e);
        }

    }
}
