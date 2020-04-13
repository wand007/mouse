package com.mouse.api.client.mall;

import com.mouse.api.base.GlobalExceptionHandler;
import com.mouse.api.commons.FootprintComm;
import com.mouse.api.commons.GoodsComm;
import com.mouse.api.commons.OrderComm;
import com.mouse.api.commons.TaskComm;
import com.mouse.api.commons.enums.RefererEnum;
import com.mouse.api.commons.req.SaveCommentReq;
import com.mouse.api.commons.req.SaveOrderReq;
import com.mouse.api.feign.mall.OrderFeign;
import com.mouse.api.service.*;
import com.mouse.api.task.OrderUnpaidTask;
import com.mouse.core.base.BusinessException;
import com.mouse.core.base.R;
import com.mouse.core.express.ExpressService;
import com.mouse.core.express.dao.ExpressInfo;
import com.mouse.core.params.dto.OrderPriceDTO;
import com.mouse.core.utils.PageNation;
import com.mouse.core.utils.RedisLock;
import com.mouse.core.utils.SnowflakeIdWorker;
import com.mouse.core.wx.WxJsPayCommon;
import com.mouse.dao.entity.operate.GrouponEntity;
import com.mouse.dao.entity.order.OrderEntity;
import com.mouse.dao.entity.order.OrderGoodsEntity;
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
    OrderComm orderComm;
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
    public R findPage(@RequestParam(name = "userId") String userId,
                      @RequestParam(name = "showType", defaultValue = "0") Integer showType,
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
        List<Map<String, Object>> result = new ArrayList<>(content.size());
        if (CollectionUtils.isEmpty(content)) {
            return R.success(PageNation.of(page, result));
        }

        for (OrderEntity orderEntity : content) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", orderEntity.getId());
            map.put("orderSn", orderEntity.getOrderSn());
            map.put("actualPrice", orderEntity.getActualPrice());
            map.put("orderStatusText", OrderUtil.orderStatusText(orderEntity));
            map.put("handleOption", OrderUtil.build(orderEntity));

            Optional<GrouponEntity> grouponEntityOptional = grouponService.findByOrderId(orderEntity.getId());
            map.put("isGroupin", grouponEntityOptional.isPresent());

            List<OrderGoodsEntity> orderGoodsEntities = orderGoodsService.findByOrderId(orderEntity.getId()).orElseGet(() -> new ArrayList<>());
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
            map.put("goodsList", orderGoodsVoList);
            result.add(map);
        }
        return R.success(PageNation.of(page, result));
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
        // 订单状态为已发货且物流信息不为空
        //"YTO", "800669400640887922"
        ExpressInfo ei = null;
        if (orderEntity.getOrderStatus().equals(OrderUtil.STATUS_SHIP)) {
            ei = expressService.getExpressInfo(orderEntity.getShipChannel(), orderEntity.getShipSn());
        }
        Map<String, Object> result = new HashMap<>();
        result.put("orderInfo", orderVo);
        result.put("orderGoods", orderGoodsEntities);
        result.put("expressInfo", ei != null ? ei : new ArrayList<>());
        return R.success(result);
    }

    /**
     * 提交订单
     *
     * @param param  订单信息，{ cartId：xxx, addressId: xxx, couponId: xxx, message: xxx, grouponRulesId: xxx,  grouponLinkId: xxx}
     * @return 提交订单操作结果
     */
    @Override
    public R submit(@RequestBody SaveOrderReq param) {
        //校验拼团规则
        orderComm.checkGrouponRule(param);
        //校验商品属性
        orderComm.checkGoodsProductRule(param);
        //计算订单金额
        OrderPriceDTO orderPriceDTO = orderComm.calculationPrice(param);
        //创建订单记录
        String orderId = orderService.save(orderPriceDTO, param);

        // 订单支付超期任务
        taskComm.addTask(new OrderUnpaidTask(orderId));

        Map<String, Object> data = new HashMap<>(8);
        data.put("orderId", orderId);
        data.put("grouponLinkId", 0);
        Integer grouponRulesId = param.getGrouponRulesId();
        if (grouponRulesId != null && grouponRulesId > 0) {
            data.put("grouponLinkId", param.getGrouponLinkId());
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
        RLock lock = redisLock.getLock(orderId);
        try {
            lock.lock();

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
     * 确认收货
     *
     * @param userId  用户ID
     * @param orderId 订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @Override
    public R confirm(@RequestParam("userId") String userId,
                     @RequestParam("orderId") String orderId) {
        RLock lock = redisLock.getLock(orderId);

        try {
            lock.lock();
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
        } finally {
            lock.unlock();
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
        RLock lock = redisLock.getLock(orderId);

        try {
            lock.lock();
            OrderEntity orderEntity = orderService.findById(orderId).orElseThrow(() -> new BusinessException("订单记录不存在"));
            if (!orderEntity.getUserId().equals(userId)) {
                return R.error("不能支付ta人的订单");
            }
            OrderHandleOption handleOption = OrderUtil.build(orderEntity);
            if (!handleOption.isDelete()) {
                return R.error("订单不能删除");
            }
            orderService.updateDeleteByOrderId(orderId, false);
        } finally {
            lock.unlock();
        }
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
