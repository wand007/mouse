package com.mouse.dao.repository.user;

import com.mouse.dao.entity.user.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer>, JpaSpecificationExecutor<CommentEntity> {

    /**
     * 查询恢复记录
     *
     * @param valueId
     * @param type
     * @return
     */
    Optional<List<CommentEntity>> findByValueIdAndType(Integer valueId, int type);

    /**
     * 统计回复数量
     *
     * @param valueId
     * @param type
     * @param deleted
     * @return
     */
    Integer countByValueIdAndTypeAndDeleted(Integer valueId, Integer type, boolean deleted);

    /**
     * 统计回复数量
     *
     * @param valueId
     * @param type
     * @param hasPicture
     * @param deleted
     * @return
     */
    Integer countByValueIdAndTypeAndHasPictureAndDeleted(Integer valueId, Integer type, boolean hasPicture, boolean deleted);
}