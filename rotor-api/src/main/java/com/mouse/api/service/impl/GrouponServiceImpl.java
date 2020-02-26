package com.mouse.api.service.impl;

import com.mouse.api.service.GrouponService;
import com.mouse.core.enums.GrouponConstant;
import com.mouse.dao.entity.operate.GrouponEntity;
import com.mouse.dao.repository.operate.GrouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Description 拼团表
 * @Date 2019-12-15
 */
@Slf4j
@Service
public class GrouponServiceImpl implements GrouponService {

    @Autowired
    GrouponRepository grouponRepository;

    @Override
    public Optional<GrouponEntity> findById(Integer grouponId) {
        return grouponRepository.findById(grouponId);
    }

    @Override
    public Optional<GrouponEntity> findByOrderId(String orderId) {
        return grouponRepository.findByOrderId(orderId);
    }

    @Override
    public Optional<List<GrouponEntity>> findByIdAndStatus(Integer grouponId) {
        return grouponRepository.findByIdAndDeletedAndStatusIn(grouponId, false,
                Arrays.asList(GrouponConstant.STATUS_ON, GrouponConstant.STATUS_SUCCEED, GrouponConstant.STATUS_FAIL));
    }

    @Override
    public Integer countById(Integer grouponId) {
        return grouponRepository.countByIdAndDeletedAndStatusIn(grouponId, false, Arrays.asList(GrouponConstant.STATUS_ON, GrouponConstant.STATUS_SUCCEED, GrouponConstant.STATUS_FAIL));
    }

    @Override
    public Long countByGrouponId(Integer grouponId) {
        return grouponRepository.count((Specification<GrouponEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));
            expressions.add(criteriaBuilder.equal(root.<Integer>get("status"), GrouponConstant.STATUS_NONE));
            expressions.add(criteriaBuilder.equal(root.<Integer>get("grouponId"), grouponId));
            return predicate;
        });
    }

    @Override
    public Long countByUserIdAndGrouponId(String userId, Integer grouponId) {
        return grouponRepository.count((Specification<GrouponEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));
            expressions.add(criteriaBuilder.equal(root.<Integer>get("status"), GrouponConstant.STATUS_NONE));
            expressions.add(criteriaBuilder.equal(root.<Integer>get("grouponId"), grouponId));
            expressions.add(criteriaBuilder.equal(root.<Integer>get("userId"), grouponId));
            return predicate;
        });
    }

    @Override
    public List<GrouponEntity> findByUserIdAndCreatorUserId(String userId) {
        List<GrouponEntity> grouponEntities = grouponRepository.findAll((Specification<GrouponEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));
            expressions.add(criteriaBuilder.equal(root.<Integer>get("userId"), userId));
            expressions.add(criteriaBuilder.equal(root.<Integer>get("creatorUserId"), userId));
            expressions.add(criteriaBuilder.equal(root.<Integer>get("grouponId"), 0));
            CriteriaBuilder.In<Short> in = criteriaBuilder.in(root.<Short>get("status"));
            for (Short id : Arrays.asList(GrouponConstant.STATUS_ON, GrouponConstant.STATUS_SUCCEED, GrouponConstant.STATUS_FAIL)) {
                in.value(id);
            }
            expressions.add(criteriaBuilder.and(in));
            return predicate;
        }, new Sort(Sort.Direction.DESC, "id"));

        return grouponEntities;
    }

    @Override
    public List<GrouponEntity> findByUserId(String userId) {
        List<GrouponEntity> grouponEntities = grouponRepository.findAll((Specification<GrouponEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));
            expressions.add(criteriaBuilder.equal(root.<Integer>get("userId"), userId));
            expressions.add(criteriaBuilder.notEqual(root.<Integer>get("grouponId"), 0));
            CriteriaBuilder.In<Short> in = criteriaBuilder.in(root.<Short>get("status"));
            for (Short id : Arrays.asList(GrouponConstant.STATUS_ON, GrouponConstant.STATUS_SUCCEED, GrouponConstant.STATUS_FAIL)) {
                in.value(id);
            }
            expressions.add(criteriaBuilder.and(in));
            return predicate;
        }, new Sort(Sort.Direction.DESC, "id"));

        return grouponEntities;
    }


    @Override
    public void createGroupon(GrouponEntity groupon) {
        grouponRepository.save(groupon);
    }
}
