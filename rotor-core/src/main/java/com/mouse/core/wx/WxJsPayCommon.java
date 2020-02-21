package com.mouse.core.wx;

import com.mouse.core.base.BusinessException;
import com.mouse.core.utils.JsonUtils;
import com.mouse.core.utils.MyBeanUtils;
import com.mouse.core.wx.param.WxNotifyParam;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ; lidongdong
 * @Description 微信JS支付
 * @Date 2019-12-26
 */
@Slf4j
public class WxJsPayCommon {


    private WXJSPayConfig wxjsPayConfig;

    private WXPay wxPay;

    public WxJsPayCommon(WXJSPayConfig wxjsPayConfig) {
        this.wxjsPayConfig = wxjsPayConfig;
        this.wxPay = new WXPay(this.wxjsPayConfig);
    }


    /**
     * 统一下单
     *
     * @param out_trade_no 订单ID
     * @param payAmount    单位：分
     * @param openId       用户唯一标识
     * @param body
     * @param attach
     * @return
     * @throws Exception
     */
    public Map<String, String> unifiedOrder(String out_trade_no, BigDecimal payAmount, String openId, String body, String attach) throws Exception {

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
        map.put("trade_type", "JSAPI");

        Map<String, String> unifiedOrder = wxPay.unifiedOrder(map);

        return unifiedOrder;
    }

    /**
     * 初始化统一下单参数
     *
     * @param orderId   订单ID
     * @param body
     * @param payAmount 单位：分
     * @param openId    用户唯一标识
     * @param attach
     * @return
     * @throws Exception
     */
    public Map<String, String> initPayMap(String orderId, BigDecimal payAmount, String openId, String body, String attach) throws Exception {
        Map<String, String> map = new LinkedHashMap(8);
        map.put("timeStamp", System.currentTimeMillis() / 1000L + "");
        map.put("nonceStr", WXPayUtil.generateNonceStr());
        map.put("signType", wxPay.getSignType().name());
        map.put("appId", wxjsPayConfig.getAppID());
        //初始化微信JS支付订单
        Map<String, String> unifiedOrderMap = this.unifiedOrder(orderId, payAmount, openId, body, attach);
        if (WXPayConstants.ReturnCode.SUCCESS.name().equals(unifiedOrderMap.get("result_code"))) {
            map.put("package", "prepay_id=" + unifiedOrderMap.get("prepay_id"));
        } else {
            log.error("预支付订单异常:" + JsonUtils.toJson(unifiedOrderMap));
            throw new BusinessException(unifiedOrderMap.get("err_code_des"));
        }

        String sign = WXPayUtil.generateSignature(map, wxjsPayConfig.getKey(), wxPay.getSignType());
        map.put("paySign", sign);
        return map;
    }

    /**
     * 订单查询
     *
     * @param out_trade_no 订单ID
     * @return
     * @throws Exception
     */
    public Map<String, String> orderQuery(String out_trade_no) throws Exception {
        Map<String, String> map = new HashMap<>(16);
        map.put("out_trade_no", out_trade_no);
        Map<String, String> orderQuery = wxPay.orderQuery(map);

        return orderQuery;
    }

    /**
     *
     *
     * @param out_trade_no 订单ID
     * @return
     * @throws Exception
     */
    /**
     * 退款申请
     *
     * @param out_trade_no  商户订单号
     * @param out_refund_no 商户退款单号
     * @param refund_fee    退款金额
     * @param total_fee     订单金额
     * @return
     * @throws Exception
     */
    public Map<String, String> refund(String out_trade_no, String out_refund_no, BigDecimal refund_fee, BigDecimal total_fee) throws Exception {
        Map<String, String> map = new HashMap<>(16);
        map.put("out_trade_no", out_trade_no);
        map.put("out_refund_no", out_refund_no);
        map.put("refund_fee", refund_fee.intValue() + "");
        map.put("total_fee", total_fee.intValue() + "");
        Map<String, String> orderQuery = wxPay.refund(map);

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
                log.error("签名失败");
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
            log.error("微信支付接口参数解析异常", e);
            throw new BusinessException("微信支付接口参数解析异常:" + notifyParam);
        }

    }


//    public static void main(String[] args) throws Exception {
//
//        WXJSPayConfig wxjspPayConfig = new WXJSPayConfig("wxa0513bb89cf940df", "1571312801", "SJsbRCHe7Z604tadp97hrzZkjIgMdRpH", "47.95.235.88", "https://bdi-h5.hvyosv.com/h5/order/wxpay/notify");
//        WxJsPayCommon wxPayCommon = new WxJsPayCommon(wxjspPayConfig);
//
//        String orderId = "1221212";
//        BigDecimal payAmount = new BigDecimal("120");
//        String openId = "ovbCw1Fosfe8rb6xM1-gcjwxmEWs";
//        String body = "慧优税支付";
//        String attach = "尼古拉斯旭支付了个锤锤";
//        Map<String, String> unifiedOrder = wxPayCommon.unifiedOrder(orderId, payAmount, openId, body, attach);
//
//        System.out.println(JsonUtils.toJson(unifiedOrder));
//
//
//        String notifyXml = "<xml><appid><![CDATA[wxa0513bb89cf940df]]></appid><attach><![CDATA[668851149310271488]]></attach><bank_type><![CDATA[OTHERS]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><device_info><![CDATA[WEB]]></device_info><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1571312801]]></mch_id><nonce_str><![CDATA[roTSXrRX5s2voM5J6ynmRiG1ORNOGBak]]></nonce_str><openid><![CDATA[ovbCw1Fosfe8rb6xM1-gcjwxmEWs]]></openid><out_trade_no><![CDATA[668851149310271488]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[61A8C7B2D9C53BA22D545DBE0086EEA7FB12042CE69369DD202E697C84B457D7]]></sign><time_end><![CDATA[20200120161807]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[4200000505202001208197307821]]></transaction_id></xml>";
//
//        System.out.println(wxPayCommon.getWxNotify(notifyXml).toString());
//
//    }


}
