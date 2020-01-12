package com.mouse.api.service.impl;

import com.mouse.api.service.CartService;
import com.mouse.dao.entity.order.CartEntity;
import com.mouse.dao.entity.resource.GoodsEntity;
import com.mouse.dao.entity.resource.GoodsProductEntity;
import com.mouse.dao.repository.order.CartRepository;
import com.mouse.dao.repository.resource.GoodsProductRepository;
import com.mouse.dao.repository.resource.GoodsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description 用户购物车服务
 * @Date 2019-12-15
 */
@Slf4j
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    GoodsProductRepository goodsProductRepository;

    @Override
    public Optional<CartEntity> findById(Integer cartId) {
        return cartRepository.findById(cartId);
    }

    /**
     * 根据用户ID查询用户购物车记录
     *
     * @param userId
     * @return
     */
    @Override
    public Optional<List<CartEntity>> findByUserId(Integer userId) {
        return cartRepository.findByUserId(userId);
    }

    /**
     * 根据ID删除用户购物车记录
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        cartRepository.deleteById(id);
    }

    /**
     * 根据用户ID和商品ID和产品ID查询用户购物车记录
     *
     * @param productId 产品ID
     * @param userId    用户ID
     * @return
     */
    @Override
    public Optional<CartEntity> findByUserIdAndProductId(Integer userId, Integer productId) {
        return cartRepository.findByUserIdAndProductId(userId, productId);
    }

    /**
     * 统计用户购物车商品数量
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public Long count(Integer userId) {
        return cartRepository.count((Specification<CartEntity>) (root, criteriaQuery, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();
            List<Expression<Boolean>> expressions = predicate.getExpressions();
            if (userId != null) {
                expressions.add(criteriaBuilder.equal(root.<Integer>get("userId"), userId));
            }
            return predicate;
        });
    }

    @Override
    public CartEntity save(Integer userId, Integer productId) {
        GoodsProductEntity product = goodsProductRepository.findById(productId).get();
        GoodsEntity goods = goodsRepository.findById(product.getGoodsId()).get();
        CartEntity cartEntity = new CartEntity();
        cartEntity.setUserId(userId);
        cartEntity.setGoodsId(goods.getId());
        cartEntity.setGoodsSn(goods.getGoodsSn());
        cartEntity.setGoodsName((goods.getName()));
        cartEntity.setProductId((product.getId()));
        if (StringUtils.isEmpty(product.getUrl())) {
            cartEntity.setPicUrl(goods.getPicUrl());
        } else {
            cartEntity.setPicUrl(product.getUrl());
        }
        cartEntity.setPrice(product.getPrice());
        cartEntity.setSpecifications(product.getSpecifications());
        cartEntity.setIsChecked(true);
        cartRepository.save(cartEntity);
        return cartEntity;
    }

    @Override
    public void updateNumberById(Integer id, Integer number) {
        cartRepository.updateNumberById(id, number);
    }

    @Override
    public void updateChecked(Integer userId, List<Integer> productIds, Boolean isChecked) {
        cartRepository.updateChecked(userId, productIds, isChecked);
    }

    @Override
    public void deleteByUserIdAndProductIdIn(Integer userId, List<String> productIds) {
        cartRepository.deleteByUserIdAndProductIdIn(userId, productIds);
    }


    @Override
    public Optional<List<CartEntity>> findByUserIdAndIsChecked(Integer userId, boolean checked) {
        return cartRepository.findByUserIdAndIsChecked(userId, checked);
    }
}
