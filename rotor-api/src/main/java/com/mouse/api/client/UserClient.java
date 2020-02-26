package com.mouse.api.client;

import com.mouse.api.base.BaseClient;
import com.mouse.api.feign.UserFeign;
import com.mouse.api.service.*;
import com.mouse.core.base.R;
import com.mouse.dao.utils.OrderUtil;
import com.mouse.dao.entity.order.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ; lidongdong
 * @Description 用户服务 API
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("user")
public class UserClient extends BaseClient implements UserFeign {

    @Autowired
    OrderService orderService;

    @Override
    public R index(String userId) {

        List<OrderEntity> orderEntities = orderService.findByUserId(userId).orElseGet(() -> Arrays.asList());

        int unpaid = 0;
        int unship = 0;
        int unrecv = 0;
        int uncomment = 0;
        for (OrderEntity orderEntity : orderEntities) {
            if (OrderUtil.isCreateStatus(orderEntity)) {
                unpaid++;
            } else if (OrderUtil.isPayStatus(orderEntity)) {
                unship++;
            } else if (OrderUtil.isShipStatus(orderEntity)) {
                unrecv++;
            } else if (OrderUtil.isConfirmStatus(orderEntity) || OrderUtil.isAutoConfirmStatus(orderEntity)) {
                uncomment += orderEntity.getComments();
            } else {
                // do nothing
            }
        }

        Map<Object, Object> orderInfo = new HashMap<Object, Object>();
        orderInfo.put("unpaid", unpaid);
        orderInfo.put("unship", unship);
        orderInfo.put("unrecv", unrecv);
        orderInfo.put("uncomment", uncomment);
        Map<Object, Object> data = new HashMap<Object, Object>(4);
        data.put("order", orderInfo);
        return R.success(data);
    }
}
