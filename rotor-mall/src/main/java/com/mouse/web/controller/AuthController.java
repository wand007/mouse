package com.mouse.web.controller;

import com.mouse.api.commons.enums.RefererEnum;
import com.mouse.api.commons.req.wx.WxLoginInfo;
import com.mouse.api.feign.mall.AuthFeign;
import com.mouse.core.base.R;
import com.mouse.core.utils.IpUtils;
import com.mouse.core.utils.RegexUtils;
import com.mouse.core.utils.WebKit;
import com.mouse.web.base.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;

/**
 * 鉴权服务
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/auth")
public class AuthController extends GlobalExceptionHandler {

    @Autowired
    private AuthFeign authFeign;


    /**
     * 账号登录
     *
     * @param username 用户名称
     * @param password 登录密码
     * @param request  请求对象
     * @return 登录结果
     */
    @PostMapping("login")
    public R login(@RequestParam(name = "username") String username,
                   @RequestParam(name = "password") String password,
                   HttpServletRequest request) {
        //忽略网络切换
        String userAgent = WebKit.getDeviceInfo(request.getHeader("User-Agent"));

        //获取ip
        String ip = IpUtils.getRealIp(request);
        return authFeign.login(username, password, RefererEnum.WX, userAgent, ip);
    }

    /**
     * 微信登录
     *
     * @param wxLoginInfo 请求内容，{ code: xxx, userInfo: xxx }
     * @return 登录结果
     */
    @PostMapping("login_by_weixin")
    public R loginByWeixin(@RequestBody WxLoginInfo wxLoginInfo) {
        return authFeign.loginByWeiXin(wxLoginInfo);
    }


    /**
     * 请求注册验证码
     * <p>
     * 这里需要一定机制防止短信验证码被滥用
     *
     * @param mobile 手机号码 { mobile }
     * @return
     */
    @PostMapping("regCaptcha")
    public R registerCaptcha(@RequestParam(name = "mobile")
                             @Pattern(regexp = RegexUtils.REGEXP_MOBILE, message = "手机号格式不正确") String mobile) {
        return authFeign.registerCaptcha(mobile);
    }

    /**
     * 账号注册
     *
     * @param username 登录账号
     * @param password 登录密码
     * @param mobile   注册手机号
     * @param code     注册短信验证码
     * @return
     */
    @PostMapping("register")
    public R register(@RequestParam(name = "username") String username,
                      @RequestParam(name = "password") String password,
                      @RequestParam(name = "mobile")
                      @Pattern(regexp = RegexUtils.REGEXP_MOBILE, message = "手机号格式不正确") String mobile,
                      @RequestParam(name = "code") String code,
                      @RequestParam(name = "wxCode", required = false) String wxCode,
                      HttpServletRequest request) {
        //忽略网络切换
        String userAgent = WebKit.getDeviceInfo(request.getHeader("User-Agent"));
        //获取ip
        String ip = IpUtils.getRealIp(request);
        return authFeign.register(username, password, mobile, RefererEnum.WX, userAgent, ip, code, wxCode);
    }

    /**
     * 请求验证码
     *
     * @param userId 用户ID
     * @param type
     * @param mobile 手机号码
     * @return
     */
    @PostMapping("captcha")
    public R captcha(@RequestParam(name = "userId") Integer userId,
                     @RequestParam(name = "type") String type,
                     @Pattern(regexp = RegexUtils.REGEXP_MOBILE, message = "手机号格式不正确") String mobile) {
        return authFeign.captcha(userId, type, mobile);
    }

    /**
     * 账号密码重置
     *
     * @param userId   用户ID
     * @param password 登录密码
     * @param mobile   注册手机号
     * @param code     注册短信验证码
     * @return
     */
    @PostMapping("reset")
    public R reset(@RequestParam(name = "userId") Integer userId,
                   @RequestParam(name = "password") String password,
                   @RequestParam(name = "mobile")
                   @Pattern(regexp = RegexUtils.REGEXP_MOBILE, message = "手机号格式不正确") String mobile,
                   @RequestParam(name = "code") String code) {
        return authFeign.reset(userId, password, mobile, code);
    }

    /**
     * 账号密码重置
     *
     * @param userId   用户ID
     * @param password 登录密码
     * @param mobile   注册手机号
     * @param code     注册短信验证码
     * @return
     */
    @PostMapping("resetPhone")
    public R resetPhone(@RequestParam(name = "userId") Integer userId,
                        @RequestParam(name = "password") String password,
                        @RequestParam(name = "mobile")
                        @Pattern(regexp = RegexUtils.REGEXP_MOBILE, message = "手机号格式不正确") String mobile,
                        @RequestParam(name = "code") String code) {
        return authFeign.resetPhone(userId, password, mobile, code);
    }

    /**
     * 账号信息更新
     *
     * @param userId   用户ID
     * @param password 登录密码
     * @param mobile   注册手机号
     * @param code     注册短信验证码
     * @return
     */
    @PostMapping("profile")
    public R profile(@RequestParam(name = "userId") Integer userId,
                     @RequestParam(name = "password") String password,
                     @RequestParam(name = "mobile")
                     @Pattern(regexp = RegexUtils.REGEXP_MOBILE, message = "手机号格式不正确") String mobile,
                     @RequestParam(name = "code") String code) {
        return authFeign.profile(userId, password, mobile, code);
    }

    /**
     * 微信手机号码绑定
     *
     * @param userId
     * @param encryptedData
     * @param iv
     * @return
     */
    @PostMapping("bindPhone")
    public R bindPhone(@RequestParam(name = "userId") Integer userId,
                       @RequestParam(name = "encryptedData") String encryptedData,
                       @RequestParam(name = "iv") String iv) {
        return authFeign.bindPhone(userId, encryptedData, iv);
    }

    /**
     * 退出登录
     *
     * @param userId
     * @return
     */
    @PostMapping("logout")
    public R logout(@RequestParam(name = "userId") Integer userId) {
        return authFeign.logout(userId);
    }

    /**
     * 用户详情
     *
     * @param userId
     * @return
     */
    @GetMapping("info")
    public R info(@RequestParam(name = "userId") Integer userId) {
        return authFeign.info(userId);
    }
}
