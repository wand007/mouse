package com.mouse.dao.repository.operate;

import com.mouse.dao.entity.operate.AdEntity;
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
public interface AdRepository extends JpaRepository<AdEntity, Integer>, JpaSpecificationExecutor<AdEntity> {

    /**
     * 查询广告列表
     *
     * @param position 广告位置
     * @param enabled  是否启动
     * @param deleted  逻辑删除
     * @return
     */
    Optional<List<AdEntity>> findByPositionAndEnabledAndDeleted(Integer position, Boolean enabled, Boolean deleted);
}