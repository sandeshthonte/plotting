package com.arundhati.backend.plotting.config;

import com.arundhati.backend.plotting.common.utils.ConvertorUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CachingService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void put(String key, Object value) {
        try {
            String jsonValue = ConvertorUtils.objectMapper.writeValueAsString(value);
            log.info("Putting object into cache, key: {}, object: {}", key, jsonValue);
            put(key, jsonValue);
        } catch (JsonProcessingException e) {
            log.error("Error serializing object to JSON", e);
            throw new RuntimeException("Error serializing object to JSON", e);
        }
    }

    public void put(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void putWithExpiry(String key, Object value, long timeInMillis) {
        try {
            String jsonValue = ConvertorUtils.objectMapper.writeValueAsString(value);
            log.info("Putting object into cache with expiry, key: {}, object: {}", key, jsonValue);
            putWithExpiry(key, jsonValue, timeInMillis);
        } catch (JsonProcessingException e) {
            log.error("Error serializing object to JSON", e);
            throw new RuntimeException("Error serializing object to JSON", e);
        }
    }

    public void putWithExpiry(String key, String value, long timeInMillis) {
        redisTemplate.opsForValue().set(key, value, timeInMillis, TimeUnit.MILLISECONDS);
    }

    public <T> T get(String key, Class<T> classType) {
        String jsonValue = redisTemplate.opsForValue().get(key);
        return parseJsonValue(jsonValue, classType);
    }

    public <T> T get(String key, TypeReference<T> typeReference) {
        String jsonValue = redisTemplate.opsForValue().get(key);
        return parseJsonValue(jsonValue, typeReference);
    }

    private <T> T parseJsonValue(String jsonValue, Class<T> valueType) {
        if (StringUtils.isEmpty(jsonValue)) {
            return null;
        }
        try {
            return ConvertorUtils.objectMapper.readValue(jsonValue, valueType);
        } catch (JsonProcessingException e) {
            log.error("Error deserializing JSON to object", e);
            throw new RuntimeException("Error deserializing JSON to object", e);
        }
    }

    private <T> T parseJsonValue(String jsonValue, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(jsonValue)) {
            return null;
        }
        try {
            return ConvertorUtils.objectMapper.readValue(jsonValue, typeReference);
        } catch (JsonProcessingException e) {
            log.error("Error deserializing JSON to object", e);
            throw new RuntimeException("Error deserializing JSON to object", e);
        }
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean containsKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public void removeKey(String key) {
        redisTemplate.delete(key);
        log.info("Removed key from cache: {}", key);
    }

    public void removeKeys(List<String> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            log.error("Keys list is empty");
            return;
        }

        keys.forEach(this::removeKey);
    }

    public void addKeys(Map<String, String> data) {
        data.forEach((key, value) -> {
            log.info("Putting object into cache, key: {}, object: {}", key, value);
            put(key, value);
        });
    }

}
