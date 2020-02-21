package com.mouse.api.commons;

import com.mouse.api.service.*;
import com.mouse.core.express.ExpressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2020-02-03
 */
@Slf4j
@Component
public class OrderComm {

    @Autowired
    CartService cartService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsAttributeService goodsAttributeService;
    @Autowired
    GoodsSpecificationService goodsSpecificationService;
    @Autowired
    ProductService productService;
    @Autowired
    GoodsIssueService goodsIssueService;
    @Autowired
    CommentService commentService;
    @Autowired
    GrouponService grouponService;
    @Autowired
    CouponService couponService;
    @Autowired
    CouponUserService couponUserService;

    @Autowired
    GrouponRulesService grouponRulesService;
    @Autowired
    CollectService collectService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderGoodsService orderGoodsService;
    @Autowired
    ExpressService expressService;
    @Autowired
    AddressService addressService;


}
