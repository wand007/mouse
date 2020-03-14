package com.mouse.dao.handler;

import com.mouse.core.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;

/**
 * @author ; lidongdong
 * @Description Json转数组
 * @Date 2020-03-14
 */
public class JsonStringArrayTypeHandler implements AttributeConverter<String[], String> {
    @Override
    public String convertToDatabaseColumn(String[] array) {
        if ((array == null || array.length == 0)) {
            array = new String[0];
        }
        return JsonUtils.toJson(array);
    }

    @Override
    public String[] convertToEntityAttribute(String parameter) {
        if (StringUtils.isBlank(parameter)) {
            return new String[0];
        }
        return JsonUtils.toObject(parameter, String[].class);
    }
}
