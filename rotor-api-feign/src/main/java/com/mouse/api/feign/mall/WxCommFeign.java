package com.mouse.api.feign.mall;

import com.mouse.api.hystrix.mall.HystrixWxCommFeign;
import com.mouse.core.base.R;
import com.mouse.core.enums.ReceiptChannelEnum;
import com.mouse.core.wx.param.WxUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ; lidongdong
 * @Description 微信通用服务 feign
 * @Date 2020-01-28
 */
@FeignClient(name = "http://ROTOR-API",
        path = "rotor-api/wx/comm",
        fallbackFactory = HystrixWxCommFeign.class)
public interface WxCommFeign {

    /**
     * 代开订单支付回调
     *
     * @param payType     支付类型
     * @param notifyParam 支付回调参数
     * @return
     */
    @PostMapping(value = "/payCallback", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    R payCallback(@RequestParam("payType") ReceiptChannelEnum payType,
                  @RequestParam("notifyParam") String notifyParam);

    /**
     * 更新用户微信H5授权信息
     *
     * @param userId     用户ID
     * @param wxUserInfo 微信H5授权信息
     * @return
     */
    @PostMapping(value = "/updateWxH5AuthCall", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    R updateWxH5AuthCall(@RequestParam("userId") String userId,
                         @RequestBody WxUserInfo wxUserInfo);
}
