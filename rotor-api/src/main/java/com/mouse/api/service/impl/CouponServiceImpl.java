package com.mouse.api.service.impl;

import com.mouse.api.service.CouponService;
import com.mouse.core.base.BusinessException;
import com.mouse.core.enums.CouponConstant;
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
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ; lidongdong
 * @Description 优惠券信息及规则表
 * @Date 2019-12-15
 */
@Slf4j
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponRepository couponRepository;
    @Autowired
    CouponUserRepository couponUserRepository;


    @Override
    public Optional<CouponEntity> findById(Integer couponId) {
        return couponRepository.findById(couponId);
    }

    @Override
    public Optional<CouponEntity> findByCode(String code) {
        return couponRepository.findByCode(code);
    }

    @Override
    public Page<CouponEntity> findPage(Integer pageNum, Integer pageSize) {
        Page<CouponEntity> page = couponRepository.findAll((Specification<CouponEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));
            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        return page;
    }


    @Override
    public Page<CouponEntity> findByUserIdPage(String userId, Integer pageNum, Integer pageSize) {

        List<CouponUserEntity> couponUserEntities = couponUserRepository.findByUserIdAndDeleted(userId, false).orElseGet(() -> new ArrayList<>());
        List<Integer> couponIds = couponUserEntities.stream().map(CouponUserEntity::getCouponId).collect(Collectors.toList());
        Page<CouponEntity> page = couponRepository.findAll((Specification<CouponEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            expressions.add(criteriaBuilder.equal(root.<Boolean>get("deleted"), false));
            if (!CollectionUtils.isEmpty(couponIds)) {
                CriteriaBuilder.In<Integer> in = criteriaBuilder.in(root.<Integer>get("id"));
                for (Integer id : couponIds) {
                    in.value(id);
                }
                expressions.add(criteriaBuilder.and(in));
            }


            return predicate;
        }, PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.DESC, "id")));

        return page;
    }


    @Override
    public Optional<List<CouponEntity>> findByIdIn(List<Integer> couponIds) {
        return couponRepository.findByDeletedAndIdIn(false, couponIds);
    }


    /**
     * 检测优惠券是否适合
     *
     * @param userId
     * @param couponId
     * @param checkedGoodsPrice
     * @return
     */
    @Override
    public CouponEntity checkCoupon(String userId, Integer couponId, Integer userCouponId, BigDecimal checkedGoodsPrice) {
        CouponEntity couponEntity = couponRepository.findById(couponId).orElseThrow(() -> new BusinessException("拼团记录不存在"));

        CouponUserEntity couponUserEntity = couponUserRepository.findById(userCouponId).orElseGet(() -> {
            CouponUserEntity entity = couponUserRepository.findByUserIdAndCouponIdAndDeleted(userId, couponId, false)
                    .orElseGet(() -> new CouponUserEntity());
            return entity;
        });
        if (!couponId.equals(couponUserEntity.getCouponId())) {
            throw new BusinessException("优惠券未领取");
        }

        // 检查是否超期
        LocalDateTime now = LocalDateTime.now();
        CouponTimeTypeEnum commentTypeEnum = CouponTimeTypeEnum.parse(couponEntity.getTimeType());
        switch (commentTypeEnum) {
            case TIME_TYPE_TIME: {
                if (now.isBefore(couponEntity.getStartTime()) || now.isAfter(couponEntity.getEndTime())) {
                    throw new BusinessException("优惠券未领取");
                }
                break;
            }
            case TIME_TYPE_DAYS: {
                LocalDateTime expired = couponUserEntity.getAddTime().plusDays(couponEntity.getDays());
                if (now.isAfter(expired)) {
                    throw new BusinessException("优惠券未领取");
                }
                break;
            }
            default: {
                throw new BusinessException("优惠券未领取");
            }
        }

        // 检测商品是否符合
        // TODO 目前仅支持全平台商品，所以不需要检测
        Short goodType = couponEntity.getGoodsType();
        if (!goodType.equals(CouponConstant.GOODS_TYPE_ALL)) {
            throw new BusinessException("优惠券未领取");
        }

        // 检测订单状态
        Short status = couponEntity.getStatus();
        if (!status.equals(CouponConstant.STATUS_NORMAL)) {
            throw new BusinessException("优惠券未领取");
        }
        // 检测是否满足最低消费
        if (checkedGoodsPrice.compareTo(couponEntity.getMin()) == -1) {
            throw new BusinessException("优惠券未领取");
        }

        return couponEntity;
    }
}
