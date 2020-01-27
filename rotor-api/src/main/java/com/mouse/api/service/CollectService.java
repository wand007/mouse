package com.mouse.api.service;

import com.mouse.dao.entity.user.CollectEntity;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-21
 */
public interface CollectService {
    /**
     * 统计用户收藏数量
     *
     * @param userId  用户ID
     * @param goodsId
     * @return
     */
    Integer countByUserIdAndValueId(Integer userId, Integer goodsId);

    /**
     * 用户收藏统计列表
     *
     * @param userId   用户ID
     * @param type     收藏类型
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<CollectEntity> findPage(Integer userId, Byte type, Integer pageNum, Integer pageSize);

    /**
     * 查询用户收藏记录
     *
     * @param userId  用户ID
     * @param type    收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID
     * @param valueId 如果type=0，则是商品ID；如果type=1，则是专题ID
     * @return
     */
    Optional<CollectEntity> findByUserIdAndValueIdAndType(Integer userId, Integer valueId, Integer type);

    /**
     * 删除收藏记录
     *
     * @param id 收藏记录ID
     */
    void deleteById(Integer id);

    /**
     * 新增收藏记录
     *
     * @param userId  用户ID
     * @param type    收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID
     * @param valueId 如果type=0，则是商品ID；如果type=1，则是专题ID
     */
    void save(Integer userId, Integer type, Integer valueId);

}
