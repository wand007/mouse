package com.mouse.api.service.impl;

import com.mouse.api.commons.req.SaveCommentReq;
import com.mouse.api.service.CommentService;
import com.mouse.core.enums.CommentTypeEnum;
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
import java.util.Optional;

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
    public Page<CommentEntity> findPage(Integer valueId, Integer type, Integer showType, Integer pageNum, Integer pageSize) {
        Page<CommentEntity> page = commentRepository.findAll((Specification<CommentEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));
            if (valueId != null) {
                expressions.add(criteriaBuilder.equal(root.<Integer>get("valueId"), valueId));
            }
            if (type != null) {
                expressions.add(criteriaBuilder.equal(root.<Integer>get("type"), type));
            }
            if (1 == showType) {
                expressions.add(criteriaBuilder.equal(root.<Boolean>get("hasPicture"), true));
            }
            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        return page;
    }

    /**
     * 统计回复数量
     *
     * @param valueId
     * @param type
     * @return
     */
    @Override
    public Integer countByValueIdAndType(Integer valueId, Integer type) {
        return commentRepository.countByValueIdAndTypeAndDeleted(valueId, type, false);
    }

    /**
     * 统计回复数量
     *
     * @param valueId
     * @param type
     * @param hasPicture
     * @return
     */
    @Override
    public Integer countByValueIdAndTypeAndHasPicture(Integer valueId, Integer type, boolean hasPicture) {
        return commentRepository.countByValueIdAndTypeAndHasPictureAndDeleted(valueId, type, hasPicture, false);
    }

    @Override
    public Optional<List<CommentEntity>> findByValueIdAndType(Integer valueId, CommentTypeEnum commentTypeEnum) {
        return commentRepository.findByValueIdAndType(valueId, commentTypeEnum.getCode());
    }


    @Override
    public void save(SaveCommentReq param) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setDeleted(false);
        commentEntity.setContent(param.getContent());
        commentEntity.setHasPicture(param.getHasPicture());
        commentEntity.setPicUrls(param.getPicUrls());
        commentEntity.setStar(param.getStar());
        commentEntity.setType(param.getType());
        commentEntity.setUserId(param.getUserId());
        commentEntity.setValueId(param.getValueId());
        commentRepository.save(commentEntity);
    }
}
