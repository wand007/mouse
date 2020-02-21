package com.mouse.core.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-30
 */
public class MD5Utils {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    public static String inputPassToFormPass(String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDbPass(String inputPass, String saltDB) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }


    /**
     * 使用ThreadLocal以空间换时间解决MD5线程安全问题
     */
    @SuppressWarnings("rawtypes")
    private static ThreadLocal threadLocal = new ThreadLocal() {
        @Override
        protected synchronized Object initialValue() {
            try {
                return MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                return null;
            }
        }
    };

    private static MessageDigest getMd5() {
        return (MessageDigest) threadLocal.get();
    }

    //-----------------------------------------------------------------------------------------------

    /**
     * 编码方法(默认UTF8)
     *
     * @param str 编码前字符串
     * @return md摘要并转16进制之后的32位字符串
     */
    public static String digest(String str) {
        getMd5().update(str.getBytes(StandardCharsets.UTF_8));
        return HexUtils.bytes2Hexstr(getMd5().digest());
    }

    /**
     * 编码方法
     *
     * @param bytes 编码前字符数组
     * @return md摘要并转16进制之后的32位字符串
     */
    public static String digest(byte[] bytes) {
        getMd5().update(bytes);
        return HexUtils.bytes2Hexstr(getMd5().digest());
    }

}
