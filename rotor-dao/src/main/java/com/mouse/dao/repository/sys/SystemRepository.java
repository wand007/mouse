package com.mouse.dao.repository.sys;

import com.mouse.dao.entity.sys.SystemEntity;
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
public interface SystemRepository extends JpaRepository<SystemEntity, Integer>, JpaSpecificationExecutor<SystemEntity> {
    /**
     * 获取全部未删除的系统配置
     *
     * @param deleted
     * @return
     */
    Optional<List<SystemEntity>> findByDeleted(boolean deleted);
}