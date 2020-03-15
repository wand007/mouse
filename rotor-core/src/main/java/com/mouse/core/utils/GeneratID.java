package com.mouse.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2020-03-15
 */
public class GeneratID {
    /**
     * 根据传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
     *
     * @param sformat YYYYMMddHHmmssSSS
     * @return
     */
    public static String getDate(String sformat) {
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        return formatter.format(new Date());
    }

    public static String getRandomNum(int num) {
        StringBuilder numStr = new StringBuilder();
        for (int i = 0; i < num; i++) {
            numStr.append((int) (10 * (Math.random())));
        }
        return numStr.toString();
    }

    /**
     * 生成id
     *
     * @return
     */
    public static String getGeneratID() {
        return getDate("YYYYMMddHHmmss") + getRandomNum(4);
    }

}
