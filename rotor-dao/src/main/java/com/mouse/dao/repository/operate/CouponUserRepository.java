package com.mouse.dao.repository.operate;

import com.mouse.dao.entity.operate.CouponUserEntity;
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
public interface CouponUserRepository extends JpaRepository<CouponUserEntity, Integer>, JpaSpecificationExecutor<CouponUserEntity> {

    /**
     * 根据用户ID查询用户优惠券
     *
     * @param userId  用户ID
     * @param deleted
     * @return
     */
    Optional<List<CouponUserEntity>> findByUserIdAndDeleted(Integer userId, Boolean deleted);
}