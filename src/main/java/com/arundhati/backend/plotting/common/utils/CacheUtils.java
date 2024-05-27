package com.arundhati.backend.plotting.common.utils;

import com.arundhati.backend.plotting.constants.AccessControlConstants;
import com.arundhati.backend.plotting.enums.CacheKey;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CacheUtils {

    public static String getCacheKey(String key, String identifier) {
        try {
            return CacheKey.valueOf(key) + AccessControlConstants.UNDERSCORE_SEPARATOR + identifier;
        }
        catch (Exception e) {
            return null;
        }

    }

    public static String getCacheKey(CacheKey cacheKey, String identifier) {
        try {
            return cacheKey.name() + AccessControlConstants.UNDERSCORE_SEPARATOR + identifier;
        }
        catch (Exception e) {
            return null;
        }

    }
}
