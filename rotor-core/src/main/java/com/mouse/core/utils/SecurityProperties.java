package com.mouse.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author ; lidongdong
 * @Description 外部properties文件 加载到内存
 * @Date 2019-12-01
 */
@Slf4j
public class SecurityProperties {
    static Map<String, String> map = new HashMap<String, String>(32);

    /**
     * 加载秘钥数据
     *
     * @param filePath 密钥文件路径
     */
    public SecurityProperties(String filePath) {

        Properties pps = new Properties();
        try {
            pps.load(new FileInputStream(filePath));
        } catch (IOException e) {
            log.error("************秘钥数据加载失败,请检查秘钥位置****************" + filePath);
        }
        //得到配置文件的名字
        Enumeration enumeration = pps.propertyNames();
        String strKey;
        while (enumeration.hasMoreElements()) {
            strKey = (String) enumeration.nextElement();
            map.put(strKey, pps.getProperty(strKey));
        }
    }

    /**
     * 获取蜜月旅行值
     *
     * @param key 密钥key
     * @return
     */
    public String getValueByKey(String key) {

        String val = map.get(key);
        if (StringUtils.isBlank(val)) {
            log.error("************秘钥加载失败,请先设置秘钥****************key:{}" + key);
        }
        return val;
    }
}
