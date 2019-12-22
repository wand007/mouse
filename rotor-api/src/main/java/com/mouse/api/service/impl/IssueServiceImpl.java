package com.mouse.api.service.impl;

import com.mouse.api.service.IssueService;
import com.mouse.dao.entity.sys.IssueEntity;
import com.mouse.dao.repository.sys.IssueRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
 * @Date 2019-12-15
 */
@Slf4j
@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    IssueRepository issueRepository;

    @Override
    public Page<IssueEntity> findPage(String question, Integer pageNum, Integer pageSize) {
        Page<IssueEntity> page = issueRepository.findAll((Specification<IssueEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));
            if (StringUtils.isNotBlank(question)) {
                expressions.add(criteriaBuilder.like(root.get("question"), "%" + question + "%"));
            }

            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        return page;
    }
}
