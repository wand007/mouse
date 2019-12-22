package com.mouse.api.client;

import com.mouse.api.base.BaseClient;
import com.mouse.api.commons.GoodsComm;
import com.mouse.api.commons.GrouponRulesComm;
import com.mouse.api.feign.FootprintFeign;
import com.mouse.api.service.FootprintService;
import com.mouse.api.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ; lidongdong
 * @Description 用户访问足迹服务 API
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("footprint")
public class FootprintClient extends BaseClient implements FootprintFeign {

    @Autowired
    GoodsComm goodsComm;
    @Autowired
    GrouponRulesComm grouponRulesComm;

    @Autowired
    FootprintService adService;
    @Autowired
    GoodsService goodsService;


}
