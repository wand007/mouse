package com.mouse.core.utils;

import com.mouse.core.base.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-30
 */
public class MyBeanUtils {

    static Logger logger = LoggerFactory.getLogger(MyBeanUtils.class);

    /**
     * bean 转成 map
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> bean2Map(Object obj) {

        if (obj == null) {
            return new HashMap<String, Object>();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    if (null == value) {
                        value = "";
                    }
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            logger.error("transBean2Map Error " + e);
            throw new BusinessException("transMap2Bean Error");
        }

        return map;

    }

    /**
     * bean 转成 map
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> bean2Map(Object obj, List<String> includeParam) {

        if (obj == null) {
            return new HashMap<String, Object>();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (includeParam.contains(key)) {
                    // 过滤class属性
                    if (!key.equals("class")) {
                        // 得到property对应的getter方法
                        Method getter = property.getReadMethod();
                        Object value = getter.invoke(obj);
                        if (null == value) {
                            value = "";
                        }
                        map.put(key, value);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("transBean2Map Error " + e);
            throw new BusinessException("transMap2Bean Error");
        }

        return map;
    }

    /**
     * bean 转成 map
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> bean2Map(Object obj, String[] excludeParam) {

        if (obj == null) {
            return new HashMap<String, Object>();
        }
        List<String> excludeParams = Arrays.asList(excludeParam);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (!excludeParams.contains(key)) {
                    // 过滤class属性
                    if (!key.equals("class") && !"metaClass".equals(key)) {
                        // 得到property对应的getter方法
                        Method getter = property.getReadMethod();
                        Object value = getter.invoke(obj);

                        if (null == value) {
                            value = "";
                        }
                        map.put(key, value);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("transBean2Map Error " + e);
            throw new BusinessException("transMap2Bean Error");
        }
        return map;
    }

    /**
     * list 返回值优化
     *
     * @param vlist        源list
     * @param includeParam 需要包含的的字段名
     * @return
     */
    public static List<Map<String, Object>> formatResult(List vlist, List<String> includeParam) {
        if (vlist == null || vlist.isEmpty()) {
            return new ArrayList<Map<String, Object>>();
        }
        List<Map<String, Object>> res = new ArrayList<Map<String, Object>>(vlist.size());
        for (Object obj : vlist) {
            res.add(bean2Map(obj, includeParam));
        }
        return res;
    }


    /**
     * bean 转成 map
     *
     * @param obj
     * @return
     */
    public static Map<String, String> bean2StringMap(Object obj) {

        if (obj instanceof Map) {
            return (Map<String, String>) obj;
        }

        if (obj == null) {
            return new HashMap();
        }
        Map<String, String> map = new LinkedHashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class") && !"metaClass".equals(key)) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    if (null == value) {
                        continue;
                    }
                    map.put(key, value + "");
                }
            }
        } catch (Exception e) {
            logger.error("transBean2Map Error " + e);
            throw new BusinessException("transMap2Bean Error");
        }
        return map;
    }

    /**
     * map 传输到obj 对象中
     *
     * @param map
     * @param obj
     */
    public static void transMap2Bean(Map<String, Object> map, Object obj) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    // 得到property对应的setter方法
                    Method setter = property.getWriteMethod();
                    setter.invoke(obj, value);
                }
            }
        } catch (Exception e) {
            logger.error("transBean2Map Error " + e);
            throw new BusinessException("transMap2Bean Error");
        }
    }

    /**
     * map map转换为 bean
     *
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T map2Bean(Map map, Class<T> clazz) {
        T t = null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            t = clazz.newInstance();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    Method setter = property.getWriteMethod();
                    // getter/setter方法
                    setter.invoke(t, value);
                }
            }
        } catch (Exception e) {
            logger.error("transBean2Map Error " + e);
            throw new BusinessException("transMap2Bean Error");
        }
        return t;
    }


    /**
     * 检查是否为非法字段
     *
     * @param s
     * @return
     */
    public static boolean checkIsBlank(String s) {
        if (StringUtils.isBlank(s)) {
            return true;
        }
        if ("null".equals(s)) {
            return true;
        }
        if ("undefined".equals(s)) {
            return true;
        }

        if ("undefine".equals(s)) {
            return true;
        }
        return false;
    }


    /**
     * javabean 参数获取
     *
     * @param javaBean
     * @param propertyName
     * @return
     */
    public static Object getProperty(Object javaBean,
                                     String propertyName) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(propertyName, javaBean.getClass());
            Method getMethod = pd.getReadMethod();  //获得get方法
            return getMethod.invoke(javaBean);
        } catch (Exception e) {
            logger.error("transBean2Map Error " + e);
            throw new BusinessException("javabean property get error :" + e.getMessage());
        }
    }


    /**
     * list 对象格式化
     *
     * @param list
     * @param ts
     * @param <T>
     * @return
     */
    public static <T> List<T> formatList(List list, Class<T> ts) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<T> result = new ArrayList<>(list.size());
        list.forEach(e -> {
            result.add(JsonUtils.toObject(JsonUtils.toJson(e), ts));
        });
        return result;
    }


    /**
     * map转 对象
     *
     * @param source
     * @param cs
     * @return
     */
    public static <T> T map2BeanByJson(Map source, Class<T> cs) {
        return JsonUtils.toObject(JsonUtils.toJson(source), cs);
    }

}
