package com.mouse.api.service.impl;

import com.mouse.api.service.KeywordService;
import com.mouse.dao.entity.sys.KeywordEntity;
import com.mouse.dao.repository.sys.KeywordRepository;
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
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
@Slf4j
@Service
public class KeywordServiceImpl implements KeywordService {
    @Autowired
    KeywordRepository keywordRepository;

    @Override
    public Optional<KeywordEntity> findDefault() {
        return keywordRepository.findByIsDefaultAndDeleted(true, false);
    }

    @Override
    public Optional<List<KeywordEntity>> findIsHots() {
        return keywordRepository.findByisHotAndDeleted(true, false);
    }

    @Override
    public Page<KeywordEntity> findByKeyword(String keyword, Integer pageNum, Integer pageSize) {
        Page<KeywordEntity> page = keywordRepository.findAll((Specification<KeywordEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            if(StringUtils.isNotBlank(keyword)){
                expressions.add(criteriaBuilder.equal(root.<String>get("keyword"), keyword));
            }
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));

            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "sortOrder")));
        return page;
    }
}
