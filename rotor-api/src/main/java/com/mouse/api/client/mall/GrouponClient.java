package com.mouse.api.client.mall;

import com.mouse.api.base.GlobalExceptionHandler;
import com.mouse.api.commons.GrouponRulesComm;
import com.mouse.api.feign.mall.GrouponFeign;
import com.mouse.api.service.*;
import com.mouse.core.base.BusinessCode;
import com.mouse.core.base.BusinessException;
import com.mouse.core.base.R;
import com.mouse.core.utils.PageNation;
import com.mouse.dao.entity.operate.GrouponEntity;
import com.mouse.dao.entity.operate.GrouponRulesEntity;
import com.mouse.dao.entity.order.OrderEntity;
import com.mouse.dao.entity.order.OrderGoodsEntity;
import com.mouse.dao.entity.resource.GoodsEntity;
import com.mouse.dao.entity.user.UserEntity;
import com.mouse.dao.utils.OrderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.*;

/**
 * @author ; lidongdong
 * @Description 团购服务 API
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("groupon")
public class GrouponClient extends GlobalExceptionHandler implements GrouponFeign {

    @Autowired
    GrouponRulesComm grouponRulesComm;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderGoodsService orderGoodsService;
    @Autowired
    GrouponService grouponService;
    @Autowired
    GrouponRulesService rulesService;
    @Autowired
    UserService userService;
    @Autowired
    GoodsService goodsService;

    @Override
    public R findPage(@Min(value = 0, message = "必须从0页开始")
                      @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                      @Min(value = 1, message = "每页必须大于1")
                      @Max(value = 300, message = "每页必须小于300")
                      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                      @RequestParam(name = "sort", defaultValue = "add_time", required = false) String sort,
                      @RequestParam(name = "order", defaultValue = "desc", required = false) String order) {
        PageNation<GrouponRulesEntity> page = grouponRulesComm.findPage(pageNum, pageSize);

        return R.success(page.getList());
    }

    @Override
    public R detail(@RequestParam(name = "userId") String userId,
                    @RequestParam(name = "grouponId") Integer grouponId) {

        Optional<GrouponEntity> grouponEntityOptional = grouponService.findById(grouponId);
        if (!grouponEntityOptional.isPresent()) {
            return R.success();
        }
        GrouponEntity groupon = grouponEntityOptional.get();

        Optional<GrouponRulesEntity> rulesEntityOptional = rulesService.findById(groupon.getRulesId());
        if (!rulesEntityOptional.isPresent()) {
            return R.success();
        }

        GrouponRulesEntity rules = rulesEntityOptional.get();

        // 订单信息
        OrderEntity order = orderService.findById(groupon.getOrderId()).orElseThrow(() ->
                new BusinessException("订单不存在")
        );
        if (!order.getUserId().equals(userId)) {
            return new R(BusinessCode.ERROR.getCode(), "不是当前用户的订单");
        }
        Map<String, Object> orderVo = new HashMap<String, Object>();
        orderVo.put("id", order.getId());
        orderVo.put("orderSn", order.getOrderSn());
        orderVo.put("addTime", order.getAddTime());
        orderVo.put("consignee", order.getConsignee());
        orderVo.put("mobile", order.getMobile());
        orderVo.put("address", order.getAddress());
        orderVo.put("goodsPrice", order.getGoodsPrice());
        orderVo.put("freightPrice", order.getFreightPrice());
        orderVo.put("actualPrice", order.getActualPrice());
        orderVo.put("orderStatusText", OrderUtil.orderStatusText(order));
        orderVo.put("handleOption", OrderUtil.build(order));
        orderVo.put("expCode", order.getShipChannel());
        orderVo.put("expNo", order.getShipSn());

        List<OrderGoodsEntity> orderGoodsList = orderGoodsService.findByOrderId(order.getId()).orElseGet(() -> Arrays.asList());
        List<Map<String, Object>> orderGoodsVoList = new ArrayList<>(orderGoodsList.size());
        for (OrderGoodsEntity orderGoods : orderGoodsList) {
            Map<String, Object> orderGoodsVo = new HashMap<>();
            orderGoodsVo.put("id", orderGoods.getId());
            orderGoodsVo.put("orderId", orderGoods.getOrderId());
            orderGoodsVo.put("goodsId", orderGoods.getGoodsId());
            orderGoodsVo.put("goodsName", orderGoods.getGoodsName());
            orderGoodsVo.put("number", orderGoods.getNumber());
            orderGoodsVo.put("retailPrice", orderGoods.getPrice());
            orderGoodsVo.put("picUrl", orderGoods.getPicUrl());
            orderGoodsVo.put("goodsSpecificationValues", orderGoods.getSpecifications());
            orderGoodsVoList.add(orderGoodsVo);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("orderInfo", orderVo);
        result.put("orderGoods", orderGoodsVoList);
        result.put("expressInfo", null);
        // 订单状态为已发货且物流信息不为空
        //"YTO", "800669400640887922"
        if (order.getOrderStatus().equals(OrderUtil.STATUS_SHIP)) {

        }

        UserEntity userEntity = userService.findById(groupon.getCreatorUserId()).orElseGet(() -> new UserEntity());
        List<UserEntity> joiners = new ArrayList<>();
        joiners.add(userEntity);
        int linkGrouponId;
        // 这是一个团购发起记录
        if (groupon.getGrouponId() == 0) {
            linkGrouponId = groupon.getId();
        } else {
            linkGrouponId = groupon.getGrouponId();

        }
        List<GrouponEntity> groupons = grouponService.findByIdAndStatus(linkGrouponId).orElseGet(() -> Arrays.asList());

        UserEntity joiner;
        for (GrouponEntity grouponItem : groupons) {
            joiner = userService.findById(grouponItem.getUserId()).orElseGet(() -> new UserEntity());
            joiners.add(joiner);
        }

        result.put("linkGrouponId", linkGrouponId);
        result.put("creator", userEntity);
        result.put("joiners", joiners);
        result.put("groupon", groupon);
        result.put("rules", rules);
        return R.success(result);
    }

    @Override
    public R join(@RequestParam(name = "grouponId") Integer grouponId) {
        GrouponEntity groupon = grouponService.findById(grouponId).orElseThrow(() -> new BusinessException("拼团记录不存在"));

        GrouponRulesEntity rules = rulesService.findById(groupon.getRulesId()).orElseThrow(() -> new BusinessException("拼团规则记录你不存在"));

        GoodsEntity goodsEntity = goodsService.findById(rules.getGoodsId()).orElseThrow(() -> new BusinessException("商品记录不存在"));

        Map<String, Object> result = new HashMap<>();
        result.put("groupon", groupon);
        result.put("goods", goodsEntity);

        return R.success(result);
    }

    @Override
    public R my(@RequestParam(name = "userId") String userId,
                @RequestParam(name = "showType") Integer showType) {

        List<GrouponEntity> myGroupons;
        if (showType == 0) {
            myGroupons = grouponService.findByUserIdAndCreatorUserId(userId);
        } else {
            myGroupons = grouponService.findByUserId(userId);
        }

        List<Map<String, Object>> grouponVoList = new ArrayList<>(myGroupons.size());

        OrderEntity order;
        GrouponRulesEntity rules;
        UserEntity creator;
        for (GrouponEntity groupon : myGroupons) {
            order = orderService.findById(groupon.getOrderId()).orElseGet(() -> new OrderEntity());
            rules = rulesService.findById(groupon.getRulesId()).orElseGet(() -> new GrouponRulesEntity());
            creator = userService.findById(groupon.getCreatorUserId()).orElseGet(() -> new UserEntity());

            Map<String, Object> grouponVo = new HashMap<>();
            //填充团购信息
            grouponVo.put("id", groupon.getId());
            grouponVo.put("groupon", groupon);
            grouponVo.put("rules", rules);
            grouponVo.put("creator", creator.getNickName());

            int linkGrouponId;
            // 这是一个团购发起记录
            if (groupon.getGrouponId() == 0) {
                linkGrouponId = groupon.getId();
                grouponVo.put("isCreator", creator.getId().equals(userId));
            } else {
                linkGrouponId = groupon.getGrouponId();
                grouponVo.put("isCreator", false);
            }
            int joinerCount = grouponService.countById(linkGrouponId);
            grouponVo.put("joinerCount", joinerCount + 1);

            //填充订单信息
            grouponVo.put("orderId", order.getId());
            grouponVo.put("orderSn", order.getOrderSn());
            grouponVo.put("actualPrice", order.getActualPrice());
            grouponVo.put("orderStatusText", OrderUtil.orderStatusText(order));

            List<OrderGoodsEntity> orderGoodsList = orderGoodsService.findByOrderId(order.getId()).orElseGet(() -> Arrays.asList());
            List<Map<String, Object>> orderGoodsVoList = new ArrayList<>(orderGoodsList.size());
            for (OrderGoodsEntity orderGoods : orderGoodsList) {
                Map<String, Object> orderGoodsVo = new HashMap<>();
                orderGoodsVo.put("id", orderGoods.getId());
                orderGoodsVo.put("goodsName", orderGoods.getGoodsName());
                orderGoodsVo.put("number", orderGoods.getNumber());
                orderGoodsVo.put("picUrl", orderGoods.getPicUrl());
                orderGoodsVoList.add(orderGoodsVo);
            }
            grouponVo.put("goodsList", orderGoodsVoList);
            grouponVoList.add(grouponVo);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("total", grouponVoList.size());
        result.put("list", grouponVoList);

        return R.success(result);
    }
}
