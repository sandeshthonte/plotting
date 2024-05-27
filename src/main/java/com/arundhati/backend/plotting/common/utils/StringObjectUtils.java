package com.arundhati.backend.plotting.common.utils;

import com.arundhati.backend.plotting.common.exception.PlottingApiException;
import com.arundhati.backend.plotting.enums.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import java.lang.reflect.Field;

@Slf4j
public class StringObjectUtils {

    public static boolean anyFieldEmpty(Object obj) {
        if (obj == null) {
            log.error("Mandatory field not passed for " + obj);
            return true;
        }

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (field.getType() == String.class && (value == null || !StringUtils.hasLength((String) value))) {
                    log.error("Mandatory field not passed for " + obj);
                    return true;
                }
                if (field.getType() == int.class && (value == null || (int) value == 0)) {
                    log.error("Mandatory field not passed for " + obj);
                    return true;
                }

                if (field.getType() == Long.class && (value == null || (long) value == 0L)) {
                    log.error("Mandatory field not passed for " + obj);
                    return true;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return true;
            }
        }
        return false;
    }

    public static void sanityCheck(Object object){
        if(anyFieldEmpty(object)){
            log.info("Mandatory parameters failed for {}", object);
            throw new PlottingApiException(ResponseCode.PX_FAILURE_011);
        }
    }
}
