package com.mouse.api.dao.repository.user;

import com.mouse.api.dao.entity.user.UserCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author ; lidongdong
 * @Description 用户优惠券
 * @Date 2019-11-26
 */
@Repository
public interface UserCouponRepository extends JpaRepository<UserCouponEntity, Integer>, JpaSpecificationExecutor<UserCouponEntity> {

}
