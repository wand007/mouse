package com.mouse.api.dao.repository.pms;

import com.mouse.api.dao.entity.pms.PmsAlbumPicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 画册图片表
 * @Date 2019-11-26
 */
@Repository
public interface PmsAlbumPicRepository extends JpaRepository<PmsAlbumPicEntity, Long>, JpaSpecificationExecutor<PmsAlbumPicEntity> {

}