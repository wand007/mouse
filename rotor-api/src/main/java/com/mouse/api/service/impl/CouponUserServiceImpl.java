package com.mouse.api.service.impl;

import com.mouse.api.service.CouponUserService;
import com.mouse.core.enums.CouponTimeTypeEnum;
import com.mouse.dao.entity.operate.CouponEntity;
import com.mouse.dao.entity.operate.CouponUserEntity;
import com.mouse.dao.repository.operate.CouponRepository;
import com.mouse.dao.repository.operate.CouponUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


/**
 * @author ; lidongdong
 * @Description 优惠券用户使用服务
 * @Date 2020-01-12
 */
@Slf4j
@Service
public class CouponUserServiceImpl implements CouponUserService {

    @Autowired
    CouponRepository couponRepository;
    @Autowired
    CouponUserRepository couponUserRepository;


    @Override
    public Optional<CouponUserEntity> findById(Integer id) {
        return couponUserRepository.findById(id);
    }

    @Override
    public Optional<List<CouponUserEntity>> findByUserId(String userId) {
        return couponUserRepository.findByUserIdAndDeleted(userId, false);
    }

    @Override
    public Integer countByCouponId(Integer couponId) {
        return couponUserRepository.countByCouponIdAndDeleted(couponId, false);
    }

    @Override
    public Integer countByUserIdAndCouponId(String userId, Integer couponId) {
        return couponUserRepository.countByUserIdAndCouponIdAndDeleted(userId, couponId, false);
    }

    @Override
    public Page<CouponUserEntity> findPage(String userId, Integer status, Integer pageNum, Integer pageSize) {
        Page<CouponUserEntity> page = couponUserRepository.findAll((Specification<CouponUserEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));
            if (userId != null) {
                expressions.add(criteriaBuilder.equal(root.<Integer>get("userId"), userId));
            }
            if (status != null) {
                expressions.add(criteriaBuilder.equal(root.<Integer>get("status"), status));
            }
            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        return page;
    }


    @Override
    public void save(String userId, Integer couponId) {
        CouponEntity couponEntity = couponRepository.findById(couponId).get();

        CouponUserEntity couponUserEntity = new CouponUserEntity();
        couponUserEntity.setCouponId(couponId);
        couponUserEntity.setUserId(userId);
        Short timeType = couponEntity.getTimeType();

        if (CouponTimeTypeEnum.TIME_TYPE_TIME.getCode() == timeType) {
            couponUserEntity.setStartTime(couponEntity.getStartTime());
            couponUserEntity.setEndTime(couponEntity.getEndTime());
        } else {
            LocalDateTime now = LocalDateTime.now();
            couponUserEntity.setStartTime(now);
            couponUserEntity.setEndTime(now.plusDays(couponEntity.getDays()));
        }
        couponUserRepository.save(couponUserEntity);
    }

    @Override
    public void update(CouponUserEntity couponUser) {
        couponUserRepository.save(couponUser);
    }

    @Override
    public Optional<CouponUserEntity> findByCouponIdAndUserId(String userId, Integer couponId) {
        return couponUserRepository.findByUserIdAndCouponIdAndDeleted(userId,couponId,false);
    }
}
