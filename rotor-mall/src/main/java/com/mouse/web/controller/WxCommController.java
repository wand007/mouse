package com.mouse.web.controller;

import com.mouse.api.commons.enums.RefererEnum;
import com.mouse.api.feign.mall.WxCommFeign;
import com.mouse.core.base.BusinessCode;
import com.mouse.core.base.R;
import com.mouse.core.enums.ReceiptChannelEnum;
import com.mouse.core.params.RotorSessionUser;
import com.mouse.core.utils.JsonUtils;
import com.mouse.core.utils.WebKit;
import com.mouse.core.wx.WXPayConstants;
import com.mouse.core.wx.WxH5Comm;
import com.mouse.core.wx.param.WxUserInfo;
import com.mouse.web.base.GlobalExceptionHandler;
import com.mouse.web.task.WxCommTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.mouse.core.config.RotorConfig.LoginPrefix.REDIS_TOKEN_USER_INFO;
import static com.mouse.core.config.RotorConfig.WxPrefix.JS_API_TOKEN_REDIS_KEY;
import static com.mouse.core.config.RotorConfig.WxPrefix.LOCK_WX_AUTH_CODE;
import static com.mouse.core.wx.WXPayConstants.WX_PAY_SERVER_ERROR_XML_S;
import static com.mouse.core.wx.WXPayConstants.WX_PAY_SUCCESS_XML_S;

/**
 * @author ; lidongdong
 * @Description 微信相关服务
 * @Date 2020-01-28
 */

@Slf4j
@Validated
@RestController
@RequestMapping("wx")
public class WxCommController extends GlobalExceptionHandler {
    @Autowired
    WxH5Comm wxH5Comm;
    @Autowired
    WxCommTask wxCommTask;
    @Autowired
    WxCommFeign wxCommFeign;


    /**
     * 微信公众号支付回调
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/pay/notify")
    public void wxPayNotify(HttpServletRequest request, HttpServletResponse response) {
        //解析微信返回参数
        String xmlMsg = WXPayConstants.readData(request);

        R r = wxCommFeign.payCallback(ReceiptChannelEnum.WX_PAY_JS, xmlMsg);
        if (null != r && BusinessCode.SUCCESS.getCode() == (r.getStatusCode())) {
            WebKit.response(response, WX_PAY_SUCCESS_XML_S);
        } else {
            WebKit.response(response, WX_PAY_SERVER_ERROR_XML_S);
        }
    }

    /**
     * 创建签名
     * https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=jsapisign
     *
     * @param url（当前网页的URL，不包含#及其后面部分。注意：对于没有只有域名没有 path 的 URL ，浏览器会自动加上 / 作为 path，如打开 http://qq.com 则获取到的 URL 为 http://qq.com/）
     * @return
     */
    @GetMapping(value = "/sign")
    public R sign(@RequestParam("url") String url) {
        Map<String, String> map = wxH5Comm.sign(url, redisTemplate.opsForValue().get(JS_API_TOKEN_REDIS_KEY + RefererEnum.WX.getCode()).toString());
        return R.success(map);
    }

    /**
     * 定时刷新用户微信token
     *
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/refererToken/05DHR3KbORlfAzkI/6hFx661ZnChvyyjy")
    public R refererToken() {
        wxCommTask.refeshToken();
        return R.success();
    }

    /**
     * 发起授权
     *
     * @return
     */
    @GetMapping(value = "/wxAuth")
    public void wxAuth(@RequestAttribute(name = "sessionUser") RotorSessionUser sessionUser,
                       HttpServletResponse response) throws IOException {
        try {
            response.sendRedirect(wxH5Comm.authorizeUrl(sessionUser.getId()));
        } catch (Exception e) {
            log.error("自然人代开-h5微信授权异常", e);

            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(JsonUtils.toJson(new R(BusinessCode.ERROR.getCode(), "网络异常,请刷新重试")));
            writer.flush();
            writer.close();
        }
    }


    /**
     * 授权回调
     *
     * @return
     */
    @RequestMapping(value = "/wxAuthCall")
    public void wxAuthCall(String code, String state, HttpServletResponse response) throws IOException {
        String key = REDIS_TOKEN_USER_INFO + state;
        RotorSessionUser rotorSessionUser = (RotorSessionUser) redisTemplate.opsForValue().get(key);

        String needAuthFlag = "false";
        StringBuilder sbUrl = new StringBuilder(wxH5Comm.getLoginSeccessUri());
        try {
            //防止微信 重复回调
            if (redisTemplate.opsForValue().setIfAbsent(LOCK_WX_AUTH_CODE + code, "1")) {
                redisTemplate.expire(LOCK_WX_AUTH_CODE + code, 1, TimeUnit.MINUTES);
                //获取用户信息
                WxUserInfo wxUserInfo = wxH5Comm.userInfo(wxH5Comm.userAccessToken(code));
                log.info("微信授权成功,userId:{},wxUserInfo:{}", rotorSessionUser.getId(), wxUserInfo.toString());

                wxCommFeign.updateWxH5AuthCall(rotorSessionUser.getId(), wxUserInfo);

                rotorSessionUser.setUnionId(wxUserInfo.getUnionid());
                rotorSessionUser.setNeedAuthFlag(false);
                rotorSessionUser.setOpenId(wxUserInfo.getOpenid());
                rotorSessionUser.setNickName(wxUserInfo.getNickname());
                rotorSessionUser.setAvatarUrl(wxUserInfo.getHeadimgurl());
                redisTemplate.opsForValue().set(key, rotorSessionUser);

                needAuthFlag = "true";
            }
        } catch (Exception e) {
            log.error("微信授权异常", e);
        }

        response.sendRedirect(sbUrl.append("?needAuthFlag=").append(needAuthFlag).toString());
    }
}
