package com.mouse.api.service;

import com.mouse.dao.entity.operate.GrouponRulesEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
public interface GrouponRulesService {
    Optional<List<GrouponRulesEntity>> findByGoodsId(Integer goodsId);

    Optional<GrouponRulesEntity> findById(Integer rulesId);

    Page<GrouponRulesEntity> findPage(Integer pageNum, Integer pageSize);
}
