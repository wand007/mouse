package com.mouse.api.service.impl;

import com.mouse.api.service.RulesService;
import com.mouse.dao.entity.operate.GrouponRulesEntity;
import com.mouse.dao.repository.operate.GrouponRepository;
import com.mouse.dao.repository.operate.GrouponRulesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
@Slf4j
@Service
public class RulesServiceImpl implements RulesService {
    @Autowired
    GrouponRepository grouponRepository;
    @Autowired
    GrouponRulesRepository grouponRulesRepository;

    @Override
    public Optional<List<GrouponRulesEntity>> findByGoodsId(Integer goodsId) {
        return grouponRulesRepository.findByGoodsIdAndDeleted(goodsId,false);
    }
}
