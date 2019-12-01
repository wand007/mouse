package com.mouse.api.dao.repository.resource;

import com.mouse.api.dao.entity.resource.CommentPictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 评价图片实体
 * @Date 2019-11-26
 */
@Repository
public interface CommentPictureRepository extends JpaRepository<CommentPictureEntity, Integer>, JpaSpecificationExecutor<CommentPictureEntity> {

}
