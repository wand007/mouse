package com.mouse.api.client;

import com.mouse.api.base.GlobalExceptionHandler;
import com.mouse.api.commons.GoodsComm;
import com.mouse.api.commons.req.CartCheckedReq;
import com.mouse.api.commons.req.SaveCartReq;
import com.mouse.api.commons.req.UpdateCartReq;
import com.mouse.api.commons.rsp.CartRsp;
import com.mouse.api.feign.CartFeign;
import com.mouse.api.service.CartService;
import com.mouse.api.service.GoodsService;
import com.mouse.api.service.ProductService;
import com.mouse.core.base.BusinessException;
import com.mouse.core.base.R;
import com.mouse.dao.entity.order.CartEntity;
import com.mouse.dao.entity.resource.GoodsEntity;
import com.mouse.dao.entity.resource.GoodsProductEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author ; lidongdong
 * @Description 用户购物车服务 API
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("cart")
public class CartClient extends GlobalExceptionHandler implements CartFeign {


    @Autowired
    GoodsComm goodsComm;
    @Autowired
    CartService cartService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    ProductService productService;

    /**
     * 用户购物车信息
     *
     * @param userId 用户ID
     * @return 用户购物车信息
     */
    @Override
    public R index(@RequestParam(name = "userId") String userId) {
        List<CartEntity> cartEntities = cartService.findByUserId(userId).orElseGet(() -> Arrays.asList());
        //商品总数量
        int goodsCount = 0;
        //已选中商品数量
        int checkedGoodsCount = 0;
        //商品总金额
        BigDecimal goodsAmount = BigDecimal.ZERO;
        //已选中商品总金额
        BigDecimal checkedGoodsAmount = BigDecimal.ZERO;
        List<CartRsp> cartList = new ArrayList<>();

        // 如果系统检查商品已删除或已下架，则系统自动删除。
        // 更好的效果应该是告知用户商品失效，允许用户点击按钮来清除失效商品。
        for (CartEntity cartEntity : cartEntities) {
            //验证购物车商品是否再售 -- 异步
            goodsComm.asyncCheckIsOnSale(cartEntity.getGoodsId(), cartEntity.getId());

            CartRsp cartRsp = new CartRsp();
            cartRsp.setGoodsId(cartEntity.getGoodsId());
            cartRsp.setGoodsName(cartEntity.getGoodsName());
            cartRsp.setGoodsSn(cartEntity.getGoodsSn());
            cartRsp.setId(cartEntity.getId());
            cartRsp.setIsChecked(cartEntity.getIsChecked());
            cartRsp.setNumber(cartEntity.getNumber());
            cartRsp.setPicUrl(cartEntity.getPicUrl());
            cartRsp.setPrice(cartEntity.getPrice());
            cartRsp.setProductId(cartEntity.getProductId());
            cartRsp.setSpecifications(cartEntity.getSpecifications());
            cartRsp.setUserId(cartEntity.getUserId());
            cartList.add(cartRsp);

            goodsCount += cartEntity.getNumber();
            goodsAmount = goodsAmount.add(cartEntity.getPrice().multiply(new BigDecimal(cartEntity.getNumber())));
            if (cartEntity.getIsChecked()) {
                checkedGoodsCount += cartEntity.getNumber();
                checkedGoodsAmount = checkedGoodsAmount.add(cartEntity.getPrice().multiply(new BigDecimal(cartEntity.getNumber())));
            }
        }

        Map<String, Object> cartTotal = new HashMap<>(8);
        cartTotal.put("goodsCount", goodsCount);
        cartTotal.put("goodsAmount", goodsAmount);
        cartTotal.put("checkedGoodsCount", checkedGoodsCount);
        cartTotal.put("checkedGoodsAmount", checkedGoodsAmount);

        Map<String, Object> result = new HashMap<>(8);
        result.put("cartList", cartList);
        result.put("cartTotal", cartTotal);
        return R.success(result);
    }


    /**
     * 加入商品到购物车
     * <p>
     * 如果已经存在购物车货品，则增加数量；
     * 否则添加新的购物车货品项。
     *
     * @param userId 用户ID
     * @param param  购物车商品信息， { goodsId: xxx, productId: xxx, number: xxx }
     * @return 加入购物车操作结果
     */
    @Override
    public R add(@RequestParam(name = "userId") String userId,
                 @RequestBody SaveCartReq param) {
        Integer productId = param.getProductId();
        Integer number = param.getNumber().intValue();

        GoodsProductEntity product = productService.findById(productId).orElseThrow(() -> new BusinessException("此规格商品不存在"));
        Integer goodsId = product.getGoodsId();
        //判断商品是否可以购买
        GoodsEntity goodsEntity = goodsService.findById(goodsId).orElseThrow(() -> new BusinessException("商品不存在"));
        if (!goodsEntity.getIsOnSale()) {
            return R.error("商品已下架");
        }

        //判断购物车中是否存在此规格商品
        Optional<CartEntity> cartEntityOptional = cartService.findByUserIdAndProductId(userId, productId);

        if (!cartEntityOptional.isPresent()) {
            //取得规格的信息,判断规格库存
            if (product == null || number > product.getNumber()) {
                return R.error("库存不足");
            }
            cartService.save(userId, param.getProductId(), number);
        } else {
            CartEntity cartEntity = cartEntityOptional.get();
            //取得规格的信息,判断规格库存
            int num = cartEntity.getNumber() + number;
            if (num > product.getNumber()) {
                return R.error("库存不足");
            }
            cartEntity.setNumber(num);
            cartService.updateNumberById(cartEntity.getId(), num);
        }

        return R.success(cartService.count(userId));
    }


    /**
     * 立即购买
     * <p>
     * 和add方法的区别在于：
     * 1. 如果购物车内已经存在购物车货品，前者的逻辑是数量添加，这里的逻辑是数量覆盖
     * 2. 添加成功以后，前者的逻辑是返回当前购物车商品数量，这里的逻辑是返回对应购物车项的ID
     *
     * @param userId 用户ID
     * @param param  购物车商品信息， { goodsId: xxx, productId: xxx, number: xxx }
     * @return 立即购买操作结果
     */
    @Override
    public R fastadd(@RequestParam(name = "userId") String userId,
                     @RequestBody SaveCartReq param) {
        Integer productId = param.getProductId();
        Integer number = param.getNumber().intValue();

        GoodsProductEntity product = productService.findById(productId).orElseThrow(() -> new BusinessException("此规格商品不存在"));
        Integer goodsId = product.getGoodsId();
        //判断商品是否可以购买
        GoodsEntity goodsEntity = goodsService.findById(goodsId).orElseThrow(() -> new BusinessException("商品不存在"));
        if (!goodsEntity.getIsOnSale()) {
            return R.error("商品已下架");
        }

        //根据商品ID和产品ID判断购物车中是否存在此规格商品
        Optional<CartEntity> cartEntityOptional = cartService.findByUserIdAndProductId(userId, productId);
        CartEntity cartEntity;
        if (!cartEntityOptional.isPresent()) {
            //取得规格的信息,判断规格库存
            if (product == null || number > product.getNumber()) {
                return R.error("库存不足");
            }
            cartEntity = cartService.save(param.getUserId(), param.getProductId(), number);
        } else {
            cartEntity = cartEntityOptional.get();
            //取得规格的信息,判断规格库存
            int num = cartEntity.getNumber() + number;
            if (num > product.getNumber()) {
                return R.error("库存不足");
            }
            cartEntity.setNumber(num);
            cartService.updateNumberById(cartEntity.getId(), num);
        }

        return R.success(cartEntity.getId());
    }


    /**
     * 修改购物车商品货品数量
     *
     * @param userId 用户ID
     * @param param  购物车商品信息， { id: xxx, goodsId: xxx, productId: xxx, number: xxx }
     * @return 修改结果
     */
    @Override
    public R update(@RequestParam(name = "userId") String userId,
                    @RequestBody UpdateCartReq param) {
        //根据商品ID和产品ID判断购物车中是否存在此规格商品
        CartEntity cartEntity = cartService.findByUserIdAndProductId(userId, param.getProductId())
                .orElseThrow(() -> new BusinessException("购物车记录不存在"));
        GoodsEntity goodsEntity = goodsService.findById(cartEntity.getGoodsId())
                .orElseThrow(() -> new BusinessException("商品已下架"));
        if (!goodsEntity.getIsOnSale()) {
            throw new BusinessException("商品已下架");
        }
        GoodsProductEntity productEntity = productService.findById(cartEntity.getProductId())
                .orElseThrow(() -> new BusinessException("商品记录不存在"));

        if (productEntity.getNumber() < param.getNumber()) {
            throw new BusinessException("库存不足");
        }
        int num = cartEntity.getNumber() + param.getNumber();
        cartService.updateNumberById(cartEntity.getId(), num);
        return R.success();
    }

    /**
     * 购物车商品货品勾选状态
     * <p>
     * 如果原来没有勾选，则设置勾选状态；如果商品已经勾选，则设置非勾选状态。
     *
     * @param userId 用户ID
     * @param param  产品ID集合
     * @return
     */
    @Override
    public R checked(@RequestParam(name = "userId") String userId,
                     @RequestBody CartCheckedReq param) {
        cartService.updateChecked(userId, param.getProductIds(), param.getIsChecked());
        return R.success();
    }


    /**
     * 购物车商品删除
     *
     * @param userId     用户ID
     * @param productIds 购物车商品信息， { productIds: xxx }
     * @return 购物车信息
     * 成功则
     * {
     * errno: 0,
     * errmsg: '成功',
     * data: xxx
     * }
     * 失败则 { errno: XXX, errmsg: XXX }
     */
    @Override
    public R delete(@RequestParam(name = "userId") String userId,
                    @RequestBody List<String> productIds) {
        cartService.deleteByUserIdAndProductIdIn(userId, productIds);
        return R.success();
    }


    /**
     * 购物车商品货品数量
     * <p>
     * 如果用户没有登录，则返回空数据。
     *
     * @param userId 用户ID
     * @return 购物车商品货品数量
     */
    @Override
    @GetMapping("count")
    public R count(@RequestParam(name = "userId") String userId) {

        return R.success(cartService.count(userId));
    }


    /**
     * 购物车下单
     *
     * @param userId    用户ID
     * @param cartId    购物车商品ID：
     *                  如果购物车商品ID是空，则下单当前用户所有购物车商品；
     *                  如果购物车商品ID非空，则只下单当前购物车商品。
     * @param addressId 收货地址ID：
     *                  如果收货地址ID是空，则查询当前用户的默认地址。
     * @param couponId  优惠券ID：
     *                  如果优惠券ID是空，则自动选择合适的优惠券。
     * @return 购物车操作结果
     */
    @Override
    @GetMapping("checkout")
    public R checkout(@RequestParam(name = "userId") String userId,
                      @RequestParam(name = "cartId") Integer cartId,
                      @RequestParam(name = "addressId") Integer addressId,
                      @RequestParam(name = "couponId") Integer couponId,
                      @RequestParam(name = "userCouponId") Integer userCouponId,
                      @RequestParam(name = "grouponRulesId") Integer grouponRulesId) {
        return R.success();
    }
}
