package com.mouse.api.client;

import com.mouse.api.base.GlobalExceptionHandler;
import com.mouse.api.commons.FootprintComm;
import com.mouse.api.commons.GoodsComm;
import com.mouse.api.commons.TaskComm;
import com.mouse.api.commons.enums.RefererEnum;
import com.mouse.api.commons.req.SaveCommentReq;
import com.mouse.api.commons.req.SaveOrderReq;
import com.mouse.api.feign.OrderFeign;
import com.mouse.api.service.*;
import com.mouse.api.system.SystemConfig;
import com.mouse.api.task.OrderUnpaidTask;
import com.mouse.core.base.BusinessException;
import com.mouse.core.base.R;
import com.mouse.core.enums.CouponUserEnum;
import com.mouse.core.enums.GrouponConstant;
import com.mouse.core.express.ExpressService;
import com.mouse.core.express.dao.ExpressInfo;
import com.mouse.core.utils.GeneratID;
import com.mouse.core.utils.RedisLock;
import com.mouse.core.utils.SnowflakeIdWorker;
import com.mouse.core.wx.WxJsPayCommon;
import com.mouse.dao.entity.operate.CouponEntity;
import com.mouse.dao.entity.operate.CouponUserEntity;
import com.mouse.dao.entity.operate.GrouponEntity;
import com.mouse.dao.entity.operate.GrouponRulesEntity;
import com.mouse.dao.entity.order.CartEntity;
import com.mouse.dao.entity.order.OrderEntity;
import com.mouse.dao.entity.order.OrderGoodsEntity;
import com.mouse.dao.entity.resource.GoodsProductEntity;
import com.mouse.dao.entity.user.AddressEntity;
import com.mouse.dao.entity.user.UserEntity;
import com.mouse.dao.utils.OrderHandleOption;
import com.mouse.dao.utils.OrderUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author ; lidongdong
 * @Description 订单服务 API
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("order")
public class OrderClient extends GlobalExceptionHandler implements OrderFeign {


    @Autowired
    RedisLock redisLock;
    @Autowired
    SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    GoodsComm goodsComm;
    @Autowired
    FootprintComm footprintComm;
    @Autowired
    TaskComm taskComm;
    @Autowired
    WxJsPayCommon wxJsPayCommon;

    @Autowired
    UserService userService;
    @Autowired
    CartService cartService;
    @Autowired
    GoodsAttributeService goodsAttributeService;
    @Autowired
    GoodsSpecificationService goodsSpecificationService;
    @Autowired
    SearchHistoryService searchHistoryService;
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
    OrderService orderService;
    @Autowired
    OrderGoodsService orderGoodsService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    ProductService productService;
    @Autowired
    ExpressService expressService;
    @Autowired
    AddressService addressService;
    @Autowired
    CollectService collectService;

    /**
     * 订单列表
     *
     * @param userId   用户ID
     * @param showType 订单信息
     * @param referer
     * @param pageNum
     * @param pageSize
     * @param sort
     * @param order
     * @return
     */
    @Override
    public R findPage(@RequestParam("userId") String userId,
                      @RequestParam(defaultValue = "0") Integer showType,
                      @RequestParam(name = "referer") RefererEnum referer,
                      @Min(value = 0, message = "必须从0页开始")
                      @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                      @Min(value = 1, message = "每页必须大于1")
                      @Max(value = 300, message = "每页必须小于300")
                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                      @RequestParam(name = "sort", defaultValue = "add_time", required = false) String sort,
                      @RequestParam(name = "order", defaultValue = "desc", required = false) String order) {
        List<Short> orderStatus = OrderUtil.orderStatus(showType);
        Page<OrderEntity> page = orderService.findByUserIdPage(userId, orderStatus, pageNum, pageSize);
        List<OrderEntity> content = page.getContent();
        if (!CollectionUtils.isEmpty(content)) {
            List<Map<String, Object>> orderVoList = new ArrayList<>(content.size());
            for (OrderEntity o : content) {
                Map<String, Object> orderVo = new HashMap<>();
                orderVo.put("id", o.getId());
                orderVo.put("orderSn", o.getOrderSn());
                orderVo.put("actualPrice", o.getActualPrice());
                orderVo.put("orderStatusText", OrderUtil.orderStatusText(o));
                orderVo.put("handleOption", OrderUtil.build(o));

                Optional<GrouponEntity> grouponEntityOptional = grouponService.findByOrderId(o.getId());
                orderVo.put("isGroupin", grouponEntityOptional.isPresent());

                List<OrderGoodsEntity> orderGoodsEntities = orderGoodsService.findByOrderId(o.getId()).orElseGet(() -> new ArrayList<>());
                List<Map<String, Object>> orderGoodsVoList = new ArrayList<>(orderGoodsEntities.size());
                for (OrderGoodsEntity orderGoods : orderGoodsEntities) {
                    Map<String, Object> orderGoodsVo = new HashMap<>();
                    orderGoodsVo.put("id", orderGoods.getId());
                    orderGoodsVo.put("goodsName", orderGoods.getGoodsName());
                    orderGoodsVo.put("number", orderGoods.getNumber());
                    orderGoodsVo.put("picUrl", orderGoods.getPicUrl());
                    orderGoodsVo.put("specifications", orderGoods.getSpecifications());
                    orderGoodsVo.put("price", orderGoods.getPrice());
                    orderGoodsVoList.add(orderGoodsVo);
                }
                orderVo.put("goodsList", orderGoodsVoList);

                orderVoList.add(orderVo);
            }
        }
        return R.success();
    }

    /**
     * 订单详情
     *
     * @param userId  用户ID
     * @param orderId 订单ID
     * @return 订单详情
     */
    @Override
    public R detail(@RequestParam("userId") String userId,
                    @RequestParam("orderId") String orderId) {
        // 订单信息
        OrderEntity orderEntity = orderService.findById(orderId).orElseThrow(() -> new BusinessException("订单不存在"));
        if (!orderEntity.getUserId().equals(userId)) {
            return R.error("不是当前用户的订单");
        }
        Map<String, Object> orderVo = new HashMap<String, Object>();
        orderVo.put("id", orderEntity.getId());
        orderVo.put("orderSn", orderEntity.getOrderSn());
        orderVo.put("message", orderEntity.getMessage());
        orderVo.put("addTime", orderEntity.getAddTime());
        orderVo.put("consignee", orderEntity.getConsignee());
        orderVo.put("mobile", orderEntity.getMobile());
        orderVo.put("address", orderEntity.getAddress());
        orderVo.put("goodsPrice", orderEntity.getGoodsPrice());
        orderVo.put("couponPrice", orderEntity.getCouponPrice());
        orderVo.put("freightPrice", orderEntity.getFreightPrice());
        orderVo.put("actualPrice", orderEntity.getActualPrice());
        orderVo.put("orderStatusText", OrderUtil.orderStatusText(orderEntity));
        orderVo.put("handleOption", OrderUtil.build(orderEntity));
        orderVo.put("expCode", orderEntity.getShipChannel());
        orderVo.put("expName", expressService.getVendorName(orderEntity.getShipChannel()));
        orderVo.put("expNo", orderEntity.getShipSn());

        List<OrderGoodsEntity> orderGoodsEntities = orderGoodsService.findByOrderId(orderEntity.getId()).orElseGet(() -> new ArrayList<>());

        Map<String, Object> result = new HashMap<>();
        result.put("orderInfo", orderVo);
        result.put("orderGoods", orderGoodsEntities);

        // 订单状态为已发货且物流信息不为空
        //"YTO", "800669400640887922"
        if (orderEntity.getOrderStatus().equals(OrderUtil.STATUS_SHIP)) {
            ExpressInfo ei = expressService.getExpressInfo(orderEntity.getShipChannel(), orderEntity.getShipSn());
            if (ei == null) {
                result.put("expressInfo", new ArrayList<>());
            } else {
                result.put("expressInfo", ei);
            }
        } else {
            result.put("expressInfo", new ArrayList<>());
        }

        return R.success(result);
    }

    /**
     * 提交订单
     *
     * @param userId 用户ID
     * @param param  订单信息，{ cartId：xxx, addressId: xxx, couponId: xxx, message: xxx, grouponRulesId: xxx,  grouponLinkId: xxx}
     * @return 提交订单操作结果
     */
    @Override
    public R submit(@RequestParam("userId") String userId, @RequestBody SaveOrderReq param) {

        Integer cartId = param.getCartId();
        Integer addressId = param.getAddressId();
        Integer couponId = param.getCouponId();
        Integer userCouponId = param.getUserCouponId();
        String message = param.getMessage();
        Integer grouponRulesId = param.getGrouponRulesId();
        Integer grouponLinkId = param.getGrouponLinkId();

        //如果是团购项目,验证活动是否有效
        if (grouponRulesId != null && grouponRulesId > 0) {
            //找不到记录
            GrouponRulesEntity grouponRulesEntity = grouponRulesService.findById(grouponRulesId).orElseThrow(() -> new BusinessException("参数异常"));

            //团购规则已经过期
            if (grouponRulesEntity.getStatus().equals(GrouponConstant.RULE_STATUS_DOWN_EXPIRE)) {
                return R.error("团购已过期!");
            }
            //团购规则已经下线
            if (grouponRulesEntity.getStatus().equals(GrouponConstant.RULE_STATUS_DOWN_ADMIN)) {
                return R.error("团购已下线!");
            }

            if (grouponLinkId != null && grouponLinkId > 0) {
                //团购人数已满
                if (grouponService.countByGrouponId(grouponLinkId) >= (grouponRulesEntity.getDiscountMember() - 1)) {
                    return R.error("团购活动人数已满!");
                }
                // NOTE
                // 这里业务方面允许用户多次开团，以及多次参团，
                // 但是会限制以下两点：
                // （1）不允许参加已经加入的团购
                if (0 != grouponService.countByUserIdAndGrouponId(userId, grouponLinkId)) {
                    return R.error("团购活动已经参加!");
                }
                // （2）不允许参加自己开团的团购
                GrouponEntity grouponEntity = grouponService.findById(grouponLinkId).orElseThrow(() -> new BusinessException("团购记录不存在"));
                if (grouponEntity.getCreatorUserId().equals(userId)) {
                    return R.error("团购活动已经参加!");
                }
            }
        }

        // 团购优惠
        BigDecimal grouponPrice = new BigDecimal(0);
        GrouponRulesEntity grouponRulesEntity = null;
        Optional<GrouponRulesEntity> grouponRulesEntityOptional = grouponRulesService.findById(grouponRulesId);
        if (grouponRulesEntityOptional.isPresent()) {
            grouponRulesEntity = grouponRulesEntityOptional.get();
            grouponPrice = grouponRulesEntity.getDiscount();
        }

        // 货品价格
        List<CartEntity> checkedGoodsList = null;
        if (cartId == null || cartId.equals(0)) {
            checkedGoodsList = cartService.findByUserId(userId).orElseGet(() -> new ArrayList<>());
        } else {
            Optional<CartEntity> cartEntityOptional = cartService.findById(cartId);
            if (cartEntityOptional.isPresent()) {
                checkedGoodsList = new ArrayList<>();
                checkedGoodsList.add(cartEntityOptional.get());
            }
        }
        if (CollectionUtils.isEmpty(checkedGoodsList)) {
            return R.error("参数值异常");
        }
        BigDecimal checkedGoodsPrice = new BigDecimal(0);
        for (CartEntity checkGoods : checkedGoodsList) {
            //  只有当团购规格商品ID符合才进行团购优惠
            if (grouponRulesEntity != null && grouponRulesEntity.getGoodsId().equals(checkGoods.getGoodsId())) {
                checkedGoodsPrice = checkedGoodsPrice.add(checkGoods.getPrice().subtract(grouponPrice).multiply(new BigDecimal(checkGoods.getNumber())));
            } else {
                checkedGoodsPrice = checkedGoodsPrice.add(checkGoods.getPrice().multiply(new BigDecimal(checkGoods.getNumber())));
            }
        }

        // 获取可用的优惠券信息
        // 使用优惠券减免的金额
        BigDecimal couponPrice = new BigDecimal(0);
        // 如果couponId=0则没有优惠券，couponId=-1则不使用优惠券
        if (couponId != 0 && couponId != -1) {
            CouponEntity coupon = couponService.checkCoupon(userId, couponId, userCouponId, checkedGoodsPrice);
            couponPrice = coupon.getDiscount();
        }


        // 根据订单商品总价计算运费，满足条件（例如88元）则免运费，否则需要支付运费（例如8元）；
        BigDecimal freightPrice = new BigDecimal(0);
        if (checkedGoodsPrice.compareTo(SystemConfig.getFreightLimit()) < 0) {
            freightPrice = SystemConfig.getFreight();
        }

        // 可以使用的其他钱，例如用户积分
        BigDecimal integralPrice = new BigDecimal(0);

        // 订单费用
        BigDecimal orderTotalPrice = checkedGoodsPrice.add(freightPrice).subtract(couponPrice).max(new BigDecimal(0));
        // 最终支付费用
        BigDecimal actualPrice = orderTotalPrice.subtract(integralPrice);

        // 收货地址
        AddressEntity addressEntity = addressService.findByIdAndUserId(addressId, userId).orElseThrow(() -> new BusinessException("收货地址记录不存在"));

        String orderId = null;
        // 订单
        OrderEntity order = new OrderEntity();
        order.setUserId(userId);
        order.setId(snowflakeIdWorker.nextId());
        order.setOrderSn(GeneratID.getGeneratID());
        order.setOrderStatus(OrderUtil.STATUS_CREATE);
        order.setConsignee(addressEntity.getName());
        order.setMobile(addressEntity.getTel());
        order.setMessage(message);
        order.setAddress(addressEntity.onAddressDetail());
        order.setGoodsPrice(checkedGoodsPrice);
        order.setFreightPrice(freightPrice);
        order.setCouponPrice(couponPrice);
        order.setIntegralPrice(integralPrice);
        order.setOrderPrice(orderTotalPrice);
        order.setActualPrice(actualPrice);
        order.setComments(checkedGoodsList.size());
        order.setDeleted(false);
        // 有团购
        order.setGrouponPrice(new BigDecimal(0));    //  团购价格
        if (grouponRulesEntity != null) {
            order.setGrouponPrice(grouponPrice);
        }

        // 添加订单表项
        orderService.add(order);
        orderId = order.getId();

        List<Integer> cartIds = new ArrayList<>();
        // 添加订单商品表项
        for (CartEntity cartGoods : checkedGoodsList) {
            // 订单商品
            OrderGoodsEntity orderGoods = new OrderGoodsEntity();
            orderGoods.setOrderId(order.getId());
            orderGoods.setGoodsId(cartGoods.getGoodsId());
            orderGoods.setGoodsSn(cartGoods.getGoodsSn());
            orderGoods.setProductId(cartGoods.getProductId());
            orderGoods.setGoodsName(cartGoods.getGoodsName());
            orderGoods.setPicUrl(cartGoods.getPicUrl());
            orderGoods.setPrice(cartGoods.getPrice());
            orderGoods.setNumber(cartGoods.getNumber());
            orderGoods.setSpecifications(cartGoods.getSpecifications());
            orderGoods.setAddTime(LocalDateTime.now());
            orderGoods.setChecked(cartGoods.getChecked());
            orderGoods.setComment(cartGoods.getNumber());
            orderGoods.setDeleted(false);
            orderGoodsService.add(orderGoods);

            cartIds.add(cartGoods.getId());
        }

        // 删除购物车里面的商品信息
        cartService.clearGoods(userId, cartIds);

        // 商品货品数量减少
        for (CartEntity checkGoods : checkedGoodsList) {
            Integer productId = checkGoods.getProductId();
            GoodsProductEntity product = productService.findById(productId).orElseGet(() -> new GoodsProductEntity());

            int remainNumber = product.getNumber() - checkGoods.getNumber();
            if (remainNumber < 0) {
                throw new BusinessException("下单的商品货品数量大于库存量");
            }
            if (productService.reduceStock(productId, checkGoods.getNumber()) == 0) {
                throw new BusinessException("商品货品库存减少失败");
            }
        }

        // 如果使用了优惠券，设置优惠券使用状态
        if (couponId != 0 && couponId != -1) {
            CouponUserEntity couponUser = couponUserService.findById(userCouponId).orElseGet(() -> new CouponUserEntity());
            couponUser.setStatus(CouponUserEnum.STATUS_USED.getCode());
            couponUser.setUsedTime(LocalDateTime.now());
            couponUser.setOrderId(orderId);
            couponUserService.update(couponUser);
        }

        //如果是团购项目，添加团购信息
        if (grouponRulesId != null && grouponRulesId > 0) {
            GrouponEntity groupon = new GrouponEntity();
            groupon.setOrderId(orderId);
            groupon.setStatus(GrouponConstant.STATUS_NONE);
            groupon.setUserId(userId);
            groupon.setRulesId(grouponRulesId);

            //参与者
            if (grouponLinkId != null && grouponLinkId > 0) {
                //参与的团购记录
                GrouponEntity baseGroupon = grouponService.findById(grouponLinkId).orElseGet(() -> new GrouponEntity());
                groupon.setCreatorUserId(baseGroupon.getCreatorUserId());
                groupon.setGrouponId(grouponLinkId);
                groupon.setShareUrl(baseGroupon.getShareUrl());
                grouponService.createGroupon(groupon);
            } else {
                groupon.setCreatorUserId(userId);
                groupon.setCreatorUserTime(LocalDateTime.now());
                groupon.setGrouponId(0);
                grouponService.createGroupon(groupon);
                grouponLinkId = groupon.getId();
            }
        }

        // 订单支付超期任务
        taskComm.addTask(new OrderUnpaidTask(orderId));

        Map<String, Object> data = new HashMap<>(8);
        data.put("orderId", orderId);
        data.put("grouponLinkId", 0);
        if (grouponRulesId != null && grouponRulesId > 0) {
            data.put("grouponLinkId", grouponLinkId);
        }
        return R.success(data);
    }

    /**
     * 取消订单
     *
     * @param userId  用户ID
     * @param orderId 订单信息，{ orderId：xxx }
     * @return 取消订单操作结果
     */
    @Override
    public R cancel(@RequestParam("userId") String userId,
                    @RequestParam("orderId") String orderId) {
        RLock lock = redisLock.lock(orderId);

        try {

            OrderEntity orderEntity = orderService.findById(orderId).orElseThrow(() -> new BusinessException("订单记录不存在"));
            if (!orderEntity.getUserId().equals(userId)) {
                return R.error("不能取消ta人的订单");
            }

            // 检测是否能够取消
            OrderHandleOption handleOption = OrderUtil.build(orderEntity);
            if (!handleOption.isCancel()) {
                return R.error("订单不能取消");
            }

            // 设置订单已取消状态
            // 设置订单已取消状态
            orderEntity.setOrderStatus(OrderUtil.STATUS_AUTO_CANCEL);
            orderEntity.setEndTime(LocalDateTime.now());
            if (orderService.updateStatusAndEndTime(orderEntity.getId(), orderEntity.getOrderStatus(), orderEntity.getEndTime()) == 0) {
                throw new BusinessException("更新数据已失效");
            }

            // 商品货品数量增加
            List<OrderGoodsEntity> orderGoodsEntities = orderGoodsService.findByOrderId(orderId).orElseGet(() -> new ArrayList<>());
            for (OrderGoodsEntity orderGoodsEntity : orderGoodsEntities) {
                Integer productId = orderGoodsEntity.getProductId();
                Integer number = orderGoodsEntity.getNumber();
                if (productService.reduceStock(productId, number) == 0) {
                    throw new BusinessException("商品货品库存增加失败");
                }
            }
            orderService.updateStatusAndEndTime(orderId, OrderUtil.STATUS_CANCEL, LocalDateTime.now());

        } finally {
            lock.unlock();
        }
        return R.success();
    }

    /**
     * 付款订单的预支付会话标识
     *
     * @param userId  用户ID
     * @param orderId 订单信息，{ orderId：xxx }
     * @return 支付订单ID
     */
    @Override
    public R prepay(@RequestParam("userId") String userId, @RequestParam("orderId") String orderId) {
        return R.success();
    }

    /**
     * 微信H5支付
     *
     * @param userId
     * @param orderId
     * @return
     */
    @Override
    public R h5pay(@RequestParam("userId") String userId, @RequestParam("orderId") String orderId) {

        OrderEntity orderEntity = orderService.findById(orderId).orElseThrow(() -> new BusinessException("订单记录不存在"));
        if (!orderEntity.getUserId().equals(userId)) {
            return R.error("不能支付ta人的订单");
        }

        // 检测是否能够取消
        OrderHandleOption handleOption = OrderUtil.build(orderEntity);
        if (!handleOption.isPay()) {
            return R.error("订单不能支付");
        }

        UserEntity userEntity = userService.findById(orderEntity.getUserId()).orElseThrow(() -> new BusinessException("用户记录不存在"));

        Map<String, String> unifiedOrderMap = null;
        String payOrderId = snowflakeIdWorker.nextId();
        try {
            // 生成三方支付信息
            unifiedOrderMap = wxJsPayCommon.initPayMap(payOrderId, orderEntity.getActualPrice(), userEntity.getWeixinOpenid(), "慧优税自然人代开服务", orderId);
        } catch (Exception e) {
            log.error("生成第三方支付信息异常,id:" + orderEntity.getId(), e);
            return R.error("第三方支付信息异常");
        }

        String payNo = unifiedOrderMap.get("prepay_id");

        return R.success(unifiedOrderMap);
    }

    /**
     * 微信付款成功或失败回调接口
     * <p>
     * TODO
     * 注意，这里pay-notify是示例地址，建议开发者应该设立一个隐蔽的回调地址
     *
     * @param request  请求内容
     * @param response 响应内容
     * @return 操作结果
     */
    @Override
    public R payNotify() {
        return R.success();
    }

    /**
     * 订单申请退款
     *
     * @param userId  用户ID
     * @param orderId 订单信息，{ orderId：xxx }
     * @return 订单退款操作结果
     */
    @Override
    public R refund(@RequestParam("userId") String userId,
                    @RequestParam("orderId") String orderId) {
        OrderEntity orderEntity = orderService.findById(orderId).orElseThrow(() -> new BusinessException("订单记录不存在"));
        if (!orderEntity.getUserId().equals(userId)) {
            return R.error("不能支付ta人的订单");
        }
        OrderHandleOption handleOption = OrderUtil.build(orderEntity);
        if (!handleOption.isRefund()) {
            return R.error("订单不能取消");
        }
        // 设置订单申请退款状态
        orderEntity.setOrderStatus(OrderUtil.STATUS_REFUND);
        orderEntity.setEndTime(LocalDateTime.now());
        if (orderService.updateStatusAndEndTime(orderEntity.getId(), orderEntity.getOrderStatus(), orderEntity.getEndTime()) == 0) {
            return R.error("订单取消失败");
        }
        //TODO 发送邮件和短信通知，这里采用异步发送
        // 有用户申请退款，邮件通知运营人员
        return R.success();
    }

    /**
     * 确认收货
     *
     * @param userId  用户ID
     * @param orderId 订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @Override
    public R confirm(@RequestParam("userId") String userId,
                     @RequestParam("orderId") String orderId) {
        OrderEntity orderEntity = orderService.findById(orderId).orElseThrow(() -> new BusinessException("订单记录不存在"));
        if (!orderEntity.getUserId().equals(userId)) {
            return R.error("不能支付ta人的订单");
        }
        OrderHandleOption handleOption = OrderUtil.build(orderEntity);
        if (!handleOption.isConfirm()) {
            return R.error("订单不能确认收货");
        }
        Integer comments = orderGoodsService.countByOrderId(orderId);
        orderEntity.setComments(comments);

        orderEntity.setOrderStatus(OrderUtil.STATUS_CONFIRM);
        orderEntity.setConfirmTime(LocalDateTime.now());
        if (orderService.updateStatusAndConfirmTime(orderEntity.getId(), orderEntity.getOrderStatus(), orderEntity.getConfirmTime()) == 0) {
            return R.error("订单确认收货失败");
        }
        return R.success();
    }

    /**
     * 删除订单
     *
     * @param userId  用户ID
     * @param orderId 订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @Override
    public R delete(@RequestParam("userId") String userId,
                    @RequestParam("orderId") String orderId) {
        OrderEntity orderEntity = orderService.findById(orderId).orElseThrow(() -> new BusinessException("订单记录不存在"));
        if (!orderEntity.getUserId().equals(userId)) {
            return R.error("不能支付ta人的订单");
        }
        OrderHandleOption handleOption = OrderUtil.build(orderEntity);
        if (!handleOption.isDelete()) {
            return R.error("订单不能删除");
        }
        orderService.updateDeleteByOrderId(orderId, false);
        return R.success();
    }

    /**
     * 待评价订单商品信息
     *
     * @param userId  用户ID
     * @param orderId 订单ID
     * @param goodsId 商品ID
     * @return 待评价订单商品信息
     */
    @Override
    public R goods(@RequestParam("userId") String userId,
                   @RequestParam("orderId") String orderId,
                   @RequestParam("goodsId") Integer goodsId) {
        List<OrderGoodsEntity> orderGoodsEntities = orderGoodsService.findByOrderIdAndGoodsId(orderId, goodsId)
                .orElseThrow(() -> new BusinessException("订单商品记录不存在"));
        int size = orderGoodsEntities.size();
        if (size == 0) {
            return R.error("订单商品记录异常");
        }
        Assert.state(size < 2, "存在多个符合条件的订单商品");

        OrderGoodsEntity orderGoods = orderGoodsEntities.get(0);
        return R.success(orderGoods);
    }

    /**
     * 评价订单商品
     *
     * @param userId 用户ID
     * @param param  订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @Override
    public R comment(@RequestParam("userId") String userId,
                     @RequestBody SaveCommentReq param) {
        return R.success();
    }
}
