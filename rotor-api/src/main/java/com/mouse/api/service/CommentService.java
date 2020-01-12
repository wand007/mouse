package com.mouse.api.service;

import com.mouse.api.commons.req.SaveCommentReq;
import com.mouse.core.enums.CommentTypeEnum;
import com.mouse.dao.entity.user.CommentEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
public interface CommentService {

    Page<CommentEntity> findPage(Integer valueId, Integer type, Integer showType, Integer pageNum, Integer pageSize);

    /**
     * 查询回复记录
     *
     * @param valueId
     * @param commentTypeEnum
     * @return
     */
    Optional<List<CommentEntity>> findByValueIdAndType(Integer valueId, CommentTypeEnum commentTypeEnum);

    /**
     * 统计回复数量
     *
     * @param valueId
     * @param type
     * @return
     */
    Integer countByValueIdAndType(Integer valueId, Integer type);

    /**
     * 统计回复数量
     *
     * @param valueId
     * @param type
     * @param hasPicture
     * @return
     */
    Integer countByValueIdAndTypeAndHasPicture(Integer valueId, Integer type, boolean hasPicture);

    /**
     * 保存回复记录
     *
     * @param param
     */
    void save(SaveCommentReq param);
}
