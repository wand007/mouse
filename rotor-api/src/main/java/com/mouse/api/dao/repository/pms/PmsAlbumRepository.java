package com.mouse.api.dao.repository.pms;

import com.mouse.api.dao.entity.pms.PmsAlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description 相册表
 * @Date 2019-11-26
 */
@Repository
public interface PmsAlbumRepository extends JpaRepository<PmsAlbumEntity, Long>, JpaSpecificationExecutor<PmsAlbumEntity> {

}