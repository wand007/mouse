package com.mouse.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ; lidongdong
 * @Description 正则表达式验证
 * @Date 2019-09-23
 */
public class RegexUtils {
    /**
     * email 地址
     */
    public final static String REGEXP_EMAIL = "\\w+@\\w+(\\.\\w+)+";

    /**
     * 年月日
     */
    public final static String REGEXP_DAY = "(\\d){4}-(\\d){2}-(\\d){2}";
    /**
     * ip地址
     */
    public final static String REGEXP_IP = "^(d{1,2}|1dd|2[0-4]d|25[0-5]).(d{1,2}|1dd|2[0-4]d|25[0-5]).(d{1,2}|1dd|2[0-4]d|25[0-5]).(d{1,2}|1dd|2[0-4]d|25[0-5])$";

    /**
     * 汉字
     */
    public final static String REGEXP_CH = "^[\\u4e00-\\u9fa5]*$";

    /**
     * 整数
     */
    public final static String REGEXP_NUM = "^-?\\d+$";

    /**
     * 手机号码
     */
    public final static String REGEXP_MOBILE = "^[1]([3-9])[0-9]{9}$";
    /**
     * 特殊字符
     */
    public final static String SPECIAL_CHAR = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

    /**
     * base64字符串
     */
    public final static String BASE_64 = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";

    public final static String REGEXP_ID_CARD = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
            "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";


//    // 一级域名提取
//    private static final String RE_TOP = "(\\w*\\.?){1}\\.(com.cn|net.cn|gov.cn|org\\.nz|org.cn|com|net|org|gov|cc|biz|info|cn|co)$";

    // 定义正则表达式，域名的根需要自定义，这里不全
    private static final String RE_TOP = "[\\w-]+\\.(com.cn|net.cn|gov.cn|org\\.nz|org.cn|com|net|org|gov|cc|biz|info|cn|co)\\b()*";


    // 二级域名提取
    private static final String RE_TWO = "(\\w*\\.?){2}\\.(com.cn|net.cn|gov.cn|org\\.nz|org.cn|com|net|org|gov|cc|biz|info|cn|co)$";

    // 三级域名提取
    private static final String RE_THREE = "(\\w*\\.?){3}\\.(com.cn|net.cn|gov.cn|org\\.nz|org.cn|com|net|org|gov|cc|biz|info|cn|co)$";

    /**
     * 检测是否是11位手机号
     *
     * @param phone
     * @return
     */
    public static Boolean isPhone(String phone) {
        if (StringUtils.isEmpty(phone)) {
            return false;
        }
        Pattern p = Pattern.compile(REGEXP_MOBILE);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    public static String getTopDomain(String url) {
        String result = url;
        try {
            Matcher matcher = Pattern.compile(RE_TOP, Pattern.CASE_INSENSITIVE).matcher(url);
            matcher.find();
            result = matcher.group();
        } catch (Exception e) {
            System.out.println("[getTopDomain ERROR]====>");
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 检测是否是base64串
     *
     * @param base64
     * @return
     */
    public static Boolean isBase64(String base64) {
        if (StringUtils.isEmpty(base64)) {
            return false;
        }
        Pattern p = Pattern.compile(BASE_64);
        Matcher m = p.matcher(base64);
        return m.matches();
    }

    /**
     * 检测是否是身份证
     *
     * @param idCard
     * @return
     */
    public static Boolean isIdCard(String idCard) {
        if (StringUtils.isEmpty(idCard)) {
            return false;
        }
        Pattern p = Pattern.compile(REGEXP_ID_CARD);
        Matcher m = p.matcher(idCard);
        return m.matches();
    }

    /**
     * 检测是否是邮箱
     *
     * @param email
     * @return
     */
    public static Boolean isEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        Pattern p = Pattern.compile(REGEXP_EMAIL);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}

