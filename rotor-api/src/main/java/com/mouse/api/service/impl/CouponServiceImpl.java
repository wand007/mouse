package com.mouse.api.service.impl;

import com.mouse.api.service.CouponService;
import com.mouse.dao.entity.operate.CouponEntity;
import com.mouse.dao.entity.resource.GoodsEntity;
import com.mouse.dao.repository.operate.CouponRepository;
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
 * @Description 优惠券信息及规则表
 * @Date 2019-12-15
 */
@Slf4j
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponRepository couponRepository;

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
    public Page<CouponEntity> findByUserIdPage(String userId, Integer pageNum, Integer pageSize) {
        Page<CouponEntity> page = couponRepository.findAll((Specification<CouponEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<String>get("userId"), userId));
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));

            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        return page;
    }
}
