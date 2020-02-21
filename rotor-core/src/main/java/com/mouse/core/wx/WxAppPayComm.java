package com.mouse.core.wx;

import com.mouse.core.base.BusinessException;
import com.mouse.core.utils.JsonUtils;
import com.mouse.core.utils.MyBeanUtils;
import com.mouse.core.wx.param.WxNotifyParam;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ; lidongdong
 * @Description 微信app支付
 * @Date 2019-12-26
 */
public class WxAppPayComm {


    private WXJSPayConfig wxjsPayConfig;

    public WxAppPayComm(WXJSPayConfig wxjsPayConfig) {
        this.wxjsPayConfig = wxjsPayConfig;
    }

    /**
     * 统一下单
     *
     * @param out_trade_no 订单ID
     * @param payAmount    单位：分
     * @param openId
     * @param body
     * @param attach
     * @return
     * @throws Exception
     */
    public Map<String, String> unifiedOrder(String out_trade_no, BigDecimal payAmount, String openId, String body, String attach) throws Exception {

        WXPay wxPay = new WXPay(this.wxjsPayConfig);
        Map<String, String> map = new HashMap<>(16);
        map.put("out_trade_no", out_trade_no);
        map.put("openid", openId);
        map.put("total_fee", payAmount.setScale(0).intValue() + "");
        map.put("notify_url", wxjsPayConfig.getNotifyUrl());
        map.put("body", body);
        map.put("attach", attach);
        map.put("device_info", "WEB");
        map.put("fee_type", "CNY");
        map.put("spbill_create_ip", wxjsPayConfig.getSpbillCreateIp());
        map.put("trade_type", "MWEB");

        Map<String, String> unifiedOrder = wxPay.unifiedOrder(map);

        return unifiedOrder;
    }

    /**
     * 订单查询
     *
     * @param out_trade_no 订单ID
     * @return
     * @throws Exception
     */
    public Map<String, String> orderQuery(String out_trade_no) throws Exception {
        WXPay wxPay = new WXPay(this.wxjsPayConfig);
        Map<String, String> map = new HashMap<>(16);
        map.put("out_trade_no", out_trade_no);
        Map<String, String> orderQuery = wxPay.orderQuery(map);

        return orderQuery;
    }

    /**
     * 退款查询
     *
     * @param out_trade_no 订单ID
     * @return
     * @throws Exception
     */
    public Map<String, String> refundQuery(String out_trade_no) throws Exception {
        WXPay wxPay = new WXPay(this.wxjsPayConfig);
        Map<String, String> map = new HashMap<>(16);
        map.put("out_trade_no", out_trade_no);
        Map<String, String> orderQuery = wxPay.refundQuery(map);

        return orderQuery;
    }

    /**
     * 验证签名回调
     *
     * @param notifyData 支付结果通知的xml格式数据
     * @return
     * @throws Exception
     */
    public Boolean isPayResultNotifySignatureValid(String notifyData) throws Exception {
        WXPay wxPay = new WXPay(this.wxjsPayConfig);
        // XML转换成map
        Map<String, String> notifyMap = WXPayUtil.xmlToMap(notifyData);
        return wxPay.isPayResultNotifySignatureValid(notifyMap);
    }

    /**
     * 解析微信 支付回调响应值
     *
     * @param notifyParam
     * @return
     * @throws Exception
     */
    public WxNotifyParam getWxNotify(String notifyParam) {

        //检验秘钥
        try {
            if (!isPayResultNotifySignatureValid(notifyParam)) {
                throw new BusinessException("签名失败");
            }
            Map stringStringMap = WXPayUtil.xmlToMap(notifyParam);
            // 支付成功
            if (!WXPayConstants.SUCCESS.equals(stringStringMap.get("return_code"))) {
                throw new BusinessException("微信支付接口异常:" + stringStringMap.get("return_msg"));
            }
            WxNotifyParam wxNotifyVO = MyBeanUtils.map2Bean(stringStringMap, WxNotifyParam.class);
            if (WXPayConstants.SUCCESS.equals(stringStringMap.get("result_code"))) {
                wxNotifyVO.setPayFlag(true);
            } else {
                wxNotifyVO.setPayFlag(false);
            }
            return wxNotifyVO;
        } catch (Exception e) {
            throw new BusinessException("微信支付接口参数解析异常:" + notifyParam);
        }

    }

    public static void main(String[] args) throws Exception {

        WXJSPayConfig wxjspPayConfig = new WXJSPayConfig("wxa0513bb89cf940df", "1571312801", "SJsbRCHe7Z604tadp97hrzZkjIgMdRpH", "", "47.95.235.88", "http://www.hvyosv.com/h5/wxpay/notify");
        WxAppPayComm wxAppPayComm = new WxAppPayComm(wxjspPayConfig);

        String orderId = "1221212";
        BigDecimal payAmount = new BigDecimal("120");
        String openId = "ovbCw1Fosfe8rb6xM1-gcjwxmEWs";
        String body = "慧优税支付";
        String attach = "尼古拉斯旭支付了个锤锤";
        Map<String, String> unifiedOrder = wxAppPayComm.unifiedOrder(orderId, payAmount, openId, body, attach);

        System.out.println(JsonUtils.toJson(unifiedOrder));

    }

}
