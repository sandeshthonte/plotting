package com.arundhati.backend.plotting.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

public class ConvertorUtils {

    public static ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectMapper anyFieldEmpty() {
        return objectMapper;
    }

    public static <T> T convertToClass(Object obj, Class<T> targetClass) {
        try {
            String jsonString = objectMapper.writeValueAsString(obj);
            return objectMapper.readValue(jsonString, targetClass);
        }
        catch (Exception e) {
            return null;
        }

    }
}
