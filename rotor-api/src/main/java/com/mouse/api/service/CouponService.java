package com.mouse.api.service;

import com.mouse.dao.entity.operate.CouponEntity;
import org.springframework.data.domain.Page;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-15
 */
public interface CouponService {
    Page<CouponEntity> findPage(Integer pageNum, Integer pageSize);

    Page<CouponEntity> findByUserIdPage(String userId, Integer pageNum, Integer pageSize);
}
