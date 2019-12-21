package com.mouse.api.service;

import com.mouse.dao.entity.operate.GrouponRulesEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
public interface RulesService {
    Optional<List<GrouponRulesEntity>> findByGoodsId(Integer goodsId);
}
