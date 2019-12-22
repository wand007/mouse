package com.mouse.api.service.impl;

import com.mouse.api.service.GrouponRulesService;
import com.mouse.core.enums.GrouponConstant;
import com.mouse.dao.entity.operate.GrouponEntity;
import com.mouse.dao.entity.operate.GrouponRulesEntity;
import com.mouse.dao.repository.operate.GrouponRepository;
import com.mouse.dao.repository.operate.GrouponRulesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description 拼团规则表
 * @Date 2019-12-21
 */
@Slf4j
@Service
public class GrouponRulesServiceImpl implements GrouponRulesService {
    @Autowired
    GrouponRepository grouponRepository;
    @Autowired
    GrouponRulesRepository grouponRulesRepository;


    @Override
    public Optional<GrouponRulesEntity> findById(Integer rulesId) {
        return grouponRulesRepository.findById(rulesId);
    }

    @Override
    public Optional<List<GrouponRulesEntity>> findByGoodsId(Integer goodsId) {
        return grouponRulesRepository.findByGoodsIdAndDeleted(goodsId, false);
    }



    @Override
    public Page<GrouponRulesEntity> findPage(Integer pageNum, Integer pageSize) {
        Page<GrouponRulesEntity> page = grouponRulesRepository.findAll((Specification<GrouponRulesEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));
            CriteriaBuilder.In<Short> in = criteriaBuilder.in(root.<Short>get("status"));
            for (Short id : Arrays.asList(GrouponConstant.STATUS_ON, GrouponConstant.STATUS_SUCCEED, GrouponConstant.STATUS_FAIL)) {
                in.value(id);
            }
            expressions.add(criteriaBuilder.and(in));
            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        return page;
    }
}
