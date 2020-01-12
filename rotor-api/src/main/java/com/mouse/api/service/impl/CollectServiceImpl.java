package com.mouse.api.service.impl;

import com.mouse.api.service.CollectService;
import com.mouse.dao.entity.user.CollectEntity;
import com.mouse.dao.repository.user.CollectRepository;
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
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
@Slf4j
@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    CollectRepository collectRepository;


    @Override
    public Integer countByUserIdAndValueId(Integer userId, Integer goodsId) {
        return collectRepository.countByUserIdAndValueIdAndDeleted(userId, goodsId, false);
    }

    @Override
    public Optional<CollectEntity> findByUserIdAndValueIdAndType(Integer userId, Integer valueId, Integer type) {
        return collectRepository.findByUserIdAndValueIdAndType(userId, valueId, type);
    }

    @Override
    public void deleteById(Integer id) {
        collectRepository.deleteById(id);
    }

    @Override
    public void save(Integer userId, Integer type, Integer valueId) {
        CollectEntity collectEntity = new CollectEntity();
        collectEntity.setDeleted(false);
        collectEntity.setType(type);
        collectEntity.setUserId(userId);
        collectEntity.setValueId(valueId);
        collectRepository.save(collectEntity);
    }

    @Override
    public Page<CollectEntity> findPage(Integer userId, Byte type, Integer pageNum, Integer pageSize) {
        Page<CollectEntity> page = collectRepository.findAll((Specification<CollectEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            if (userId != null) {
                expressions.add(criteriaBuilder.equal(root.<Integer>get("userId"), userId));
            }
            if (type != null) {
                expressions.add(criteriaBuilder.equal(root.<Integer>get("type"), type));
            }
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));

            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        return page;
    }
}
