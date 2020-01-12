package com.mouse.api.service.impl;

import com.mouse.api.service.GoodsService;
import com.mouse.dao.entity.resource.GoodsEntity;
import com.mouse.dao.repository.resource.GoodsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
import java.util.Optional;

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
    public Optional<GoodsEntity> findById(Integer id) {
        return goodsRepository.findById(id);
    }

    @Override
    public Integer countByIsOnSale() {
        return goodsRepository.countByIsOnSaleAndDeleted(true,false);
    }

    @Override
    public Optional<GoodsEntity> findByIdAndIsOnSale(String goodsId) {
        return goodsRepository.countByIdAndIsOnSaleAndDeleted(goodsId,true,false);
    }

    @Override
    public Optional<List<GoodsEntity>> findByIds(List<Integer> goodsIds) {
        return goodsRepository.findByIdIn(goodsIds);
    }

    @Override
    public Page<GoodsEntity> findByIsNewAndIsOnSaleAndPage(Integer pageNum, Integer pageSize) {
        Page<GoodsEntity> page = goodsRepository.findAll((Specification<GoodsEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("isOnSale"), true));
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("isNew"), true));

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


    @Override
    public Page<GoodsEntity> findPage(Integer categoryId, Integer brandId, String keyword, Boolean isHot, Boolean isNew,
                                      Integer pageNum, Integer pageSize, String sort, String order) {
        Page<GoodsEntity> page = goodsRepository.findAll((Specification<GoodsEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            if (categoryId != null) {
                expressions.add(criteriaBuilder.equal(root.<Integer>get("categoryId"), categoryId));
            }
            if (brandId != null) {
                expressions.add(criteriaBuilder.equal(root.<Integer>get("brandId"), brandId));
            }
            if (isHot != null) {
                expressions.add(criteriaBuilder.equal(root.<Boolean>get("isHot"), isHot));
            }
            if (isNew != null) {
                expressions.add(criteriaBuilder.equal(root.<Boolean>get("isNew"), isNew));
            }
            if (StringUtils.isNotBlank(keyword)) {
                expressions.add(criteriaBuilder.equal(root.<String>get("keyword"), keyword));
            }
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));

            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        return page;
    }


    @Override
    public List<GoodsEntity> findList(Integer brandId, String keyword, Boolean isHot, Boolean isNew) {
        List<GoodsEntity> page = goodsRepository.findAll((Specification<GoodsEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            if (brandId != null) {
                expressions.add(criteriaBuilder.equal(root.<Integer>get("brandId"), brandId));
            }
            if (isHot != null) {
                expressions.add(criteriaBuilder.equal(root.<Boolean>get("isHot"), isHot));
            }
            if (isNew != null) {
                expressions.add(criteriaBuilder.equal(root.<Boolean>get("isNew"), isNew));
            }
            if (StringUtils.isNotBlank(keyword)) {
                expressions.add(criteriaBuilder.like(root.get("keywords"), "%" + keyword + "%"));
                expressions.add(criteriaBuilder.like(root.get("name"), "%" + keyword + "%"));
            }
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));

            return predicate;
        }, new Sort(Sort.Direction.DESC, "id"));

        return page;
    }

    @Override
    public Page<GoodsEntity> findByCategoryIdPage(Integer categoryId, Integer pageNum, Integer pageSize) {
        Page<GoodsEntity> page = goodsRepository.findAll((Specification<GoodsEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<String>get("categoryId"), categoryId));
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));

            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        return page;
    }
}
