package com.mouse.api.commons;

import com.mouse.api.commons.rsp.GrouponRuleRsp;
import com.mouse.api.service.GoodsService;
import com.mouse.api.service.GrouponRulesService;
import com.mouse.core.utils.PageNation;
import com.mouse.dao.entity.operate.GrouponRulesEntity;
import com.mouse.dao.entity.resource.GoodsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-22
 */
@Slf4j
@Component
public class GrouponRulesComm {
    @Autowired
    GoodsService goodsService;
    @Autowired
    GrouponRulesService grouponRulesService;

    public PageNation<GrouponRulesEntity> findPage(Integer pageNum, Integer pageSize) {
        Page<GrouponRulesEntity> page = grouponRulesService.findPage(pageNum, pageSize);
        List<GrouponRulesEntity> content = page.getContent();
        List<GrouponRuleRsp> result = new ArrayList<>(content.size());
        if (!CollectionUtils.isEmpty(content)) {
            for (GrouponRulesEntity rule : content) {
                Integer goodsId = rule.getGoodsId();
                Optional<GoodsEntity> goodsEntityOptional = goodsService.findById(goodsId);
                if (!goodsEntityOptional.isPresent()) {
                    continue;
                }
                GoodsEntity goodsEntity = goodsEntityOptional.get();
                GrouponRuleRsp grouponRuleVo = new GrouponRuleRsp();
                grouponRuleVo.setId(goodsEntity.getId());
                grouponRuleVo.setName(goodsEntity.getName());
                grouponRuleVo.setBrief(goodsEntity.getBrief());
                grouponRuleVo.setPicUrl(goodsEntity.getPicUrl());
                grouponRuleVo.setCounterPrice(goodsEntity.getCounterPrice());
                grouponRuleVo.setRetailPrice(goodsEntity.getRetailPrice());
                grouponRuleVo.setGrouponPrice(goodsEntity.getRetailPrice().subtract(rule.getDiscount()));
                grouponRuleVo.setGrouponDiscount(rule.getDiscount());
                grouponRuleVo.setGrouponMember(rule.getDiscountMember());
                grouponRuleVo.setExpireTime(rule.getExpireTime());
                result.add(grouponRuleVo);
            }
        }
        return PageNation.of(page, result);
    }
}
