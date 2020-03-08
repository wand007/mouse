package com.mouse.api.feign;

import com.mouse.api.commons.enums.RefererEnum;
import com.mouse.api.commons.req.wx.WxLoginInfo;
import com.mouse.api.hystrix.HystrixAuthFeign;
import com.mouse.core.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ; lidongdong
 * @Description 鉴权服务
 * @Date 2020-03-07
 */
@FeignClient(name = "http://ROTOR-API",
        path = "rotor-api/auth",
        fallbackFactory = HystrixAuthFeign.class)
public interface AuthFeign {

    /**
     *
     *
     * @param username 用户名称
     * @param password 登录密码
     * @return 登录结果
     */
    /**
     * 账号登录
     *
     * @param username  用户名称
     * @param password  登录密码
     * @param referer   来源
     * @param userAgent 登录客户端
     * @param landingIP 登录IP
     * @return
     */
    @PostMapping("login")
    R login(@RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "referer") RefererEnum referer,
            @RequestParam(name = "userAgent") String userAgent,
            @RequestParam(name = "landingIP") String landingIP);

    /**
     * 微信登录
     *
     * @param wxLoginInfo 请求内容，{ code: xxx, userInfo: xxx }
     * @return 登录结果
     */
    @PostMapping("login/weixin")
    R loginByWeiXin(@RequestBody WxLoginInfo wxLoginInfo);


    /**
     * 请求注册验证码
     * <p>
     * 这里需要一定机制防止短信验证码被滥用
     *
     * @param mobile 手机号码 { mobile }
     * @return
     */
    @PostMapping("regCaptcha")
    R registerCaptcha(@RequestParam(name = "mobile") String mobile);

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
    R register(@RequestParam(name = "username") String username,
               @RequestParam(name = "password") String password,
               @RequestParam(name = "mobile") String mobile,
               @RequestParam(name = "referer") RefererEnum referer,
               @RequestParam(name = "userAgent") String userAgent,
               @RequestParam(name = "lastLoginIp") String lastLoginIp,
               @RequestParam(name = "code") String code,
               @RequestParam(name = "wxCode", required = false) String wxCode);

    /**
     * 请求验证码
     *
     * @param userId 用户ID
     * @param type
     * @param mobile 手机号码
     * @return
     */
    @PostMapping("captcha")
    R captcha(@RequestParam(name = "userId") Integer userId,
              @RequestParam(name = "type") String type,
              @RequestParam(name = "mobile") String mobile);

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
    R reset(@RequestParam(name = "userId") Integer userId,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "mobile") String mobile,
            @RequestParam(name = "code") String code);

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
    R resetPhone(@RequestParam(name = "userId") Integer userId,
                 @RequestParam(name = "password") String password,
                 @RequestParam(name = "mobile") String mobile,
                 @RequestParam(name = "code") String code);

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
    R profile(@RequestParam(name = "userId") Integer userId,
              @RequestParam(name = "password") String password,
              @RequestParam(name = "mobile") String mobile,
              @RequestParam(name = "code") String code);

    /**
     * 微信手机号码绑定
     *
     * @param userId
     * @param encryptedData
     * @param iv
     * @return
     */
    @PostMapping("bindPhone")
    R bindPhone(@RequestParam(name = "userId") Integer userId,
                @RequestParam(name = "encryptedData") String encryptedData,
                @RequestParam(name = "iv") String iv);

    /**
     * 退出登录
     *
     * @param userId
     * @return
     */
    @PostMapping("logout")
    R logout(@RequestParam(name = "userId") Integer userId);

    /**
     * 用户详情
     *
     * @param userId
     * @return
     */
    @GetMapping("info")
    R info(@RequestParam(name = "userId") Integer userId);
}
