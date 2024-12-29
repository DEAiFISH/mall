package com.deaifish.mall.converter;

import com.alibaba.fastjson2.JSONObject;
import jakarta.persistence.AttributeConverter;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 15:09
 */
public class StringJSONObjectConverter implements AttributeConverter<JSONObject, String> {
    @Override
    public String convertToDatabaseColumn(JSONObject jsonObject) {
        return jsonObject.toJSONString();
    }

    @Override
    public JSONObject convertToEntityAttribute(String s) {
        return JSONObject.parseObject(s);
    }
}
