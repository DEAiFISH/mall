package com.deaifish.mall.jpa.converter;

import com.alibaba.fastjson.JSON;
import jakarta.persistence.AttributeConverter;

import java.util.List;

/**
 * @description List<String> 转 String 类型转换器
 *
 * @author DEAiFISH
 * @date 2024/12/24 21:40
 */
public class StringListConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return JSON.toJSONString(attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return JSON.parseArray(dbData, String.class);
    }
}