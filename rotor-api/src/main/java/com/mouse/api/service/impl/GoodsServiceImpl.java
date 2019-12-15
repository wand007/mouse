package com.mouse.api.service.impl;

import com.mouse.api.service.GoodsService;
import com.mouse.dao.entity.resource.GoodsEntity;
import com.mouse.dao.repository.resource.GoodsRepository;
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
import java.util.List;

/**
 * @author ; lidongdong
 * @Description 商品基本信息表
 * @Date 2019-12-15
 */
@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsRepository goodsRepository;

    @Override
    public Page<GoodsEntity> findByIsNewAndIsOnSaleAndPage(Integer pageNum, Integer pageSize) {
        Page<GoodsEntity> page = goodsRepository.findAll((Specification<GoodsEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("isOnSale"), true));
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("isNew"), true));
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));

            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        return page;
    }

    @Override
    public Page<GoodsEntity> findByIsHot(Integer pageNum, Integer pageSize) {
        Page<GoodsEntity> page = goodsRepository.findAll((Specification<GoodsEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("isHot"), true));
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));

            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        return page;
    }


    @Override
    public Page<GoodsEntity> findByCategory(List<Integer> categoryIds, Integer pageNum, Integer pageSize) {
        Page<GoodsEntity> page = goodsRepository.findAll((Specification<GoodsEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();

            expressions.add(criteriaBuilder.equal(root.<Boolean>get("isHot"), true));
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));

            if (!CollectionUtils.isEmpty(categoryIds)) {
                CriteriaBuilder.In<Integer> in = criteriaBuilder.in(root.<Integer>get("categoryId"));
                for (Integer categoryId : categoryIds) {
                    in.value(categoryId);
                    expressions.add(criteriaBuilder.and(in));
                }
            }
            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        return page;
    }
}
