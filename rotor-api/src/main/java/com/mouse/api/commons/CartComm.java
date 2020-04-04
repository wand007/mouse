package com.mouse.api.commons;

import com.mouse.api.commons.req.SaveCartReq;
import com.mouse.api.service.CartService;
import com.mouse.api.service.GoodsService;
import com.mouse.api.service.ProductService;
import com.mouse.core.base.BusinessException;
import com.mouse.dao.entity.order.CartEntity;
import com.mouse.dao.entity.resource.GoodsEntity;
import com.mouse.dao.entity.resource.GoodsProductEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-12-15
 */
@Slf4j
@Component
public class CartComm {
    @Autowired
    CartService cartService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    ProductService productService;

    /**
     * 保存或者修改购物车记录
     * <p>
     * 如果已经存在购物车货品，则增加数量；
     * 否则添加新的购物车货品项。
     *
     * @param userId 用户ID
     * @param param  购物车商品信息
     * @return 购物车ID
     */
    public Integer saveOrUpdate(String userId, SaveCartReq param) {
        Integer productId = param.getProductId();
        Integer number = param.getNumber().intValue();

        GoodsProductEntity product = productService.findById(productId).orElseThrow(() -> new BusinessException("此规格商品不存在"));
        //判断商品是否可以购买
        GoodsEntity goodsEntity = goodsService.findById(product.getGoodsId()).orElseThrow(() -> new BusinessException("商品不存在"));
        if (!goodsEntity.getIsOnSale()) {
            throw new BusinessException("商品已下架");
        }
        CartEntity cartEntity;
        //判断购物车中是否存在此规格商品
        Optional<CartEntity> cartEntityOptional = cartService.findByUserIdAndProductId(userId, productId);
        if (cartEntityOptional.isPresent()) {
            cartEntity = cartEntityOptional.get();
            //取得规格的信息,判断规格库存
            int num = cartEntity.getNumber() + number;
            if (product == null || num > product.getNumber()) {
                throw new BusinessException("库存不足");
            }
            cartEntity.setNumber(num);
            cartService.updateNumberById(cartEntity.getId(), num);
        } else {
            //取得规格的信息,判断规格库存
            if (product == null || number > product.getNumber()) {
                throw new BusinessException("库存不足");
            }
            cartEntity = cartService.save(userId, param.getProductId(), number);
        }
        return cartEntity.getId();
    }

}
