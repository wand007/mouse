package com.mouse.dao.repository.user;

import com.mouse.dao.entity.user.FootprintEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Repository
public interface FootprintRepository extends JpaRepository<FootprintEntity, Integer>, JpaSpecificationExecutor<FootprintEntity> {
    /**
     * 删除用户访问记录
     *
     * @param id     记录ID
     * @param userId 用户ID
     * @return
     */
    Integer deleteByIdAndUserId(String id, Integer userId);
}