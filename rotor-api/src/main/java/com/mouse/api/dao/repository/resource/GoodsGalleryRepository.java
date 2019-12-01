package com.mouse.api.dao.repository.resource;

import com.mouse.api.dao.entity.resource.GoodsGalleryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 商品顶部轮播图
 * @Date 2019-11-26
 */
@Repository
public interface GoodsGalleryRepository extends JpaRepository<GoodsGalleryEntity, Integer>, JpaSpecificationExecutor<GoodsGalleryEntity> {
}
