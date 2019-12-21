package com.mouse.api.service.impl;

import com.mouse.api.service.CommentService;
import com.mouse.dao.entity.user.CommentEntity;
import com.mouse.dao.repository.user.CommentRepository;
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
 * @Description
 * @Date 2019-12-21
 */
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public Page<CommentEntity> findByValueIdPage(Integer valueId, Integer pageNum, Integer pageSize) {
        Page<CommentEntity> page = commentRepository.findAll((Specification<CommentEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<Integer>get("valueId"), valueId));
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));

            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        return page;
    }
}
