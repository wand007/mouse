package com.mouse.core.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2020-01-05
 */
public class PropertiesUtils {
    public PropertiesUtils() {
    }

    public static String getProperty(String file, String key, String defaultValue) {
        ResourceBundle rb = ResourceBundle.getBundle(file);

        try {
            String e = rb.getString(key);
            System.out.println(key + "=" + e);
            return e;
        } catch (Exception var5) {
            return defaultValue;
        }
    }

    public static String getProperty(String file, String key, String encode, String defaultValue) {
        ResourceBundle rb = ResourceBundle.getBundle(file);

        try {
            String e = rb.getString(key);
            e = new String(e.getBytes("ISO-8859-1"), encode);
            System.out.println(key + "=" + e);
            return e;
        } catch (Exception var6) {
            return defaultValue;
        }
    }

    public static Map<String, String> getValuesByPrefix(String file, String prefix) {
        HashMap values = new HashMap();
        ResourceBundle rb = ResourceBundle.getBundle(file);

        try {
            Iterator e = rb.keySet().iterator();

            while (e.hasNext()) {
                String key = (String) e.next();
                if (key.startsWith(prefix)) {
                    String value = rb.getString(key);
                    System.out.println(key + "=" + e);
                    values.put(key, value);
                }
            }

            return values;
        } catch (Exception var7) {
            return values;
        }
    }
}
