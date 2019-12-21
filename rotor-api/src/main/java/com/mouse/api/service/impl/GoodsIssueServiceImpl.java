package com.mouse.api.service.impl;

import com.mouse.api.service.GoodsIssueService;
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
 * @Date 2019-12-21
 */
@Slf4j
@Service
public class GoodsIssueServiceImpl implements GoodsIssueService {
    @Autowired
    IssueRepository issueRepository;

    @Override
    public Page<IssueEntity> findByquestionPage(String question, Integer pageNum, Integer pageSize, String sort, String order) {
        Page<IssueEntity> page = issueRepository.findAll((Specification<IssueEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            if (StringUtils.isNotBlank(question)) {
                expressions.add(criteriaBuilder.equal(root.<String>get("question"), question));
            }
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));
            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        return page;
    }
}
