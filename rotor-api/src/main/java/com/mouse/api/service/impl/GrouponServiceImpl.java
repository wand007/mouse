package com.mouse.api.service.impl;

import com.mouse.api.service.GrouponService;
import com.mouse.dao.entity.operate.GrouponEntity;
import com.mouse.dao.entity.resource.BrandEntity;
import com.mouse.dao.repository.operate.GrouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * @author ; lidongdong
 * @Description 拼团表
 * @Date 2019-12-15
 */
@Slf4j
@Service
public class GrouponServiceImpl implements GrouponService {

    @Autowired
    GrouponRepository grouponRepository;


    @Override
    public Page<GrouponEntity> findPage(Integer pageNum, Integer pageSize) {
        Page<GrouponEntity> page = grouponRepository.findAll((Specification<GrouponEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));

            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        return page;
    }
}
