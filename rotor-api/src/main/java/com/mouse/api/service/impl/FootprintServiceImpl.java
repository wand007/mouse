package com.mouse.api.service.impl;

import com.mouse.api.service.FootprintService;
import com.mouse.dao.entity.user.FootprintEntity;
import com.mouse.dao.repository.user.FootprintRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
@Slf4j
@Service
public class FootprintServiceImpl implements FootprintService {
    @Autowired
    FootprintRepository footprintRepository;

    @Async
    @Override
    public void asyncSave(Integer userId, Integer goodsId) {
        FootprintEntity footprint = new FootprintEntity();
        footprint.setUserId(userId);
        footprint.setGoodsId(goodsId);
        footprint.setDeleted(false);
        footprintRepository.save(footprint);
    }


    @Override
    public void delete(Integer userId, String id) {
        footprintRepository.deleteByIdAndUserId(id, userId);
    }

    @Override
    public Page<FootprintEntity> findPage(Integer userId, Integer pageNum, Integer pageSize) {
        Page<FootprintEntity> page = footprintRepository.findAll((Specification<FootprintEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));
            if (userId != null) {
                expressions.add(criteriaBuilder.equal(root.<Integer>get("userId"), userId));
            }

            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        return page;
    }
}
