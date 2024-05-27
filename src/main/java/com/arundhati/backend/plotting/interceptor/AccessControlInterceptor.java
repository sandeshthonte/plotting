package com.arundhati.backend.plotting.interceptor;

import com.arundhati.backend.plotting.dtos.RequestDTOs;
import com.arundhati.backend.plotting.service.AccessControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import org.apache.commons.lang3.StringUtils;

import static com.arundhati.backend.plotting.constants.AccessControlConstants.*;

@Component
@Order(4)
@SuppressWarnings({"findsecbugs:XSS_SERVLET"})
public class AccessControlInterceptor implements HandlerInterceptor {

    @Autowired
    private AccessControlService accessControlService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {

            RequestDTOs.AccessControlDTO accessControlDTO= new RequestDTOs.AccessControlDTO(request.getHeader(PERMISSIONS), request.getHeader(ROLES));
            return true;
//            if (StringObjectUtils.anyFieldEmpty(accessControlDTO)) {
//                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Cannot delete role as it is associated with one or more users or permissions.");
//                return false;
//            }
//
//            Boolean hasAccess = accessControlService.hasAccess(accessControlDTO);
//            if (!hasAccess) {
//                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Cannot delete role as it is associated with one or more users or permissions.");
//                return false;
//            }
//
//            return true;
        }
        catch(Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Cannot delete role as it is associated with one or more users or permissions.");
            return false;
        }
    }
}
