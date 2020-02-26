package com.mouse.api.service;

import com.mouse.api.commons.req.SaveAddressReq;
import com.mouse.dao.entity.user.AddressEntity;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description 用户收货地址服务
 * @Date 2019-12-15
 */
public interface AddressService {

    /**
     * 分页列表查询
     *
     * @param userId   用户ID
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<AddressEntity> findPage(String userId, Integer pageNum, Integer pageSize);

    /**
     * 根据记录ID查询用户收货地址记录
     *
     * @param id 收货地址ID
     * @return
     */
    Optional<AddressEntity> findById(Integer id);

    /**
     * 根据记录ID和用户ID查询用户收货地址记录
     *
     * @param id     收货地址ID
     * @param userId 用户ID
     * @return
     */
    Optional<AddressEntity> findByIdAndUserId(Integer id, String userId);

    /**
     * 新增/修改用户收货地址记录
     *
     * @param param
     */
    void save(SaveAddressReq param);

    /**
     * 更新收货地址
     *
     * @param param 用户收货地址
     * @return 添加操作结果
     */
    void update(SaveAddressReq param);

    /**
     * 删除收货地址
     *
     * @param id     收货地址ID
     * @return 删除操作结果
     */
    void delete(Integer id);
}
