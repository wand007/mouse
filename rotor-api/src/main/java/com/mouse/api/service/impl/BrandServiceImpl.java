package com.mouse.api.service.impl;

import com.mouse.api.service.BrandService;
import com.mouse.dao.entity.operate.TopicEntity;
import com.mouse.dao.entity.resource.BrandEntity;
import com.mouse.dao.repository.resource.BrandRepository;
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
 * @Description 品牌商表
 * @Date 2019-12-15
 */
@Slf4j
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;


    @Override
    public Page<BrandEntity> findPage(Integer pageNum, Integer pageSize) {
        Page<BrandEntity> page = brandRepository.findAll((Specification<BrandEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));

            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        return page;
    }
}
