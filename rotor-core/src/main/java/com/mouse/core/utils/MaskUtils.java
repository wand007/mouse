package com.mouse.core.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ; lidongdong
 * @Description 邮箱 身份证 手机号脱敏
 * @Date 2019-12-16
 */
public class MaskUtils {

    private MaskUtils() {
    }

    /**
     * 名字脱敏
     * 规则，张三丰，脱敏为：张*丰
     *
     * @param name
     * @return
     */
    public static String maskName(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }
        String myName = null;
        char[] chars = name.toCharArray();
        if (chars.length == 1) {
            myName = name;
        }
        if (chars.length == 2) {
            myName = name.replaceFirst(name.substring(1), "*");
        }
        if (chars.length > 2) {
            myName = name.replaceAll(name.substring(1, chars.length - 1), "*");
        }
        return myName;
    }

    /**
     * 隐藏手机号 155****7649
     *
     * @param mobile
     * @return
     */
    public static String maskMobile(String mobile) {
        if (StringUtils.isEmpty(mobile) || (mobile.length() != 11)) {
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 隐藏邮箱信息 coup***@sina.com
     *
     * @param email
     * @return
     */
    public static String maskEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return email;
        }
        return email.replaceAll("(\\w+)\\w{3}@(\\w+)", "$1***@$2");
    }

    /**
     * 隐藏身份证号码 362201******191811
     *
     * @param id
     * @return
     */
    public static String maskIdCardNo(String id) {
        if (StringUtils.isEmpty(id) || (id.length() < 8)) {
            return id;
        }
        return id.replaceAll("(?<=\\w{6})\\w(?=\\w{6})", "*");
    }

    /**
     * [银行卡号] 前六位，后四位，其他用星号隐藏每位1个星号<例子:622848*********5616>
     *
     * @param cardNum
     * @return
     */
    public static String maskBankCard(String cardNum) {
        if (StringUtils.isBlank(cardNum)) {
            return "";
        }
        return StringUtils.left(cardNum, 6).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(cardNum, 4), StringUtils.length(cardNum), "*"), "******"));
    }

    /**
     * [公司开户银行联号] 公司开户银行联行号,显示前两位，其他用星号隐藏，每位1个星号<例子:622848*************>
     *
     * @param code
     * @return
     */
    public static String maskCnapsCode(String code) {
        if (StringUtils.isBlank(code)) {
            return "";
        }
        return StringUtils.rightPad(StringUtils.left(code, 6), StringUtils.length(code), "*");
    }

    /**
     * [地址] 只显示到地区，不显示详细地址；我们要对个人信息增强保护<例子：北京市海淀区****>
     *
     * @param address
     * @param sensitiveSize 敏感信息长度
     * @return
     */
    public static String maskAddress(String address, int sensitiveSize) {
        if (StringUtils.isBlank(address)) {
            return "";
        }
        int length = StringUtils.length(address);
        return StringUtils.rightPad(StringUtils.left(address, length - sensitiveSize), length, "*");
    }

    public static void main(String[] args) {
        System.out.println(MaskUtils.maskMobile("15574307649"));
        System.out.println(MaskUtils.maskIdCardNo("362201199009191811"));
        System.out.println(MaskUtils.maskEmail("coupman@sina.com"));
        System.out.println(MaskUtils.maskBankCard("6228482462893085616"));
        System.out.println(MaskUtils.maskCnapsCode("6228482462893085616"));

    }
}
