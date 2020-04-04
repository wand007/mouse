package com.mouse.api.client.mall;

import com.mouse.api.base.GlobalExceptionHandler;
import com.mouse.api.feign.mall.WxCommFeign;
import com.mouse.api.service.OrderService;
import com.mouse.core.base.R;
import com.mouse.core.enums.ReceiptChannelEnum;
import com.mouse.core.utils.RedisLock;
import com.mouse.core.wx.WxJsPayCommon;
import com.mouse.core.wx.param.OrderToNotifyParam;
import com.mouse.core.wx.param.WxNotifyParam;
import com.mouse.core.wx.param.WxUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author ; lidongdong
 * @Description 微信通用服务 API
 * @Date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("wx")
public class WxCommClient extends GlobalExceptionHandler implements WxCommFeign {
    @Autowired
    RedisLock redisLock;
    @Autowired
    WxJsPayCommon wxJsPayCommon;
    @Autowired
    OrderService orderService;

    /**
     * 代开订单支付回调
     *
     * @param receiptChannelEnum 支付类型
     * @param notifyParam        支付回调参数
     * @return
     */
    @Override
    public R payCallback(ReceiptChannelEnum receiptChannelEnum, String notifyParam) {
        WxNotifyParam wxNotifyParam = wxJsPayCommon.getWxNotify(notifyParam);

        OrderToNotifyParam params = new OrderToNotifyParam();
        params.setAmount(new BigDecimal(wxNotifyParam.getTotal_fee()));
        params.setNotifyResult(wxNotifyParam.getPayFlag());
        params.setOrderId(wxNotifyParam.getOut_trade_no());
        params.setTransactionId(wxNotifyParam.getTransaction_id());
        params.setUserIdentify(wxNotifyParam.getOpenid());

        RLock lock = redisLock.getLock(params.getOrderId());
        try {
            lock.lock();


        } finally {
            lock.unlock();
        }
        return R.success();
    }

    /**
     * 更新用户微信H5授权信息
     *
     * @param userId     用户ID
     * @param wxUserInfo 微信H5授权信息
     * @return
     */
    @Override
    public R updateWxH5AuthCall(String userId, WxUserInfo wxUserInfo) {
        return null;
    }
}
