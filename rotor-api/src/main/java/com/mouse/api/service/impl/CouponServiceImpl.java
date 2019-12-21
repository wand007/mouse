package com.mouse.api.service.impl;

import com.mouse.api.service.CouponService;
import com.mouse.dao.entity.operate.CouponEntity;
import com.mouse.dao.entity.operate.CouponUserEntity;
import com.mouse.dao.repository.operate.CouponRepository;
import com.mouse.dao.repository.operate.CouponUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ; lidongdong
 * @Description 优惠券信息及规则表
 * @Date 2019-12-15
 */
@Slf4j
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponRepository couponRepository;
    @Autowired
    CouponUserRepository couponUserRepository;

    @Override
    public Page<CouponEntity> findPage(Integer pageNum, Integer pageSize) {
        Page<CouponEntity> page = couponRepository.findAll((Specification<CouponEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));

            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        return page;
    }

    @Override
    public Page<CouponEntity> findByUserIdPage(Integer userId, Integer pageNum, Integer pageSize) {

        List<CouponUserEntity> couponUserEntities = couponUserRepository.findByUserIdAndDeleted(userId, false).orElseGet(() -> new ArrayList<>());
        List<Integer> couponIds = couponUserEntities.stream().map(CouponUserEntity::getCouponId).collect(Collectors.toList());
        Page<CouponEntity> page = couponRepository.findAll((Specification<CouponEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));
            if (!CollectionUtils.isEmpty(couponIds)) {
                CriteriaBuilder.In<Integer> in = criteriaBuilder.in(root.<Integer>get("id"));
                for (Integer id : couponIds) {
                    in.value(id);
                }
                expressions.add(criteriaBuilder.and(in));
            }


            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        return page;
    }
}
