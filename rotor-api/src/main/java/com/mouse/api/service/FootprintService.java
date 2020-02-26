package com.mouse.api.service;

import com.mouse.dao.entity.user.FootprintEntity;
import org.springframework.data.domain.Page;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
public interface FootprintService {
    void save(String userId, Integer goodsId);

    /**
     * 删除用户访问记录
     *
     * @param id     记录ID
     * @param userId 用户ID
     * @return
     */
    void delete(String userId, String id);

    /**
     * 查询用户足迹分页列表记录
     *
     * @param userId   用户ID
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<FootprintEntity> findPage(String userId, Integer pageNum, Integer pageSize);

}
