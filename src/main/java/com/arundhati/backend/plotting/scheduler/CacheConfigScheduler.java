package com.arundhati.backend.plotting.scheduler;


import com.arundhati.backend.plotting.config.CachingService;
import com.arundhati.backend.plotting.constants.AccessControlConstants;
import com.arundhati.backend.plotting.dtos.JPAResponseDTO;
import com.arundhati.backend.plotting.enums.CacheKey;
import com.arundhati.backend.plotting.enums.PermissionEnums;
import com.arundhati.backend.plotting.repository.AccessControlRepository;
import com.arundhati.backend.plotting.repository.PermissionsRepository;
import com.arundhati.backend.plotting.repository.RolesRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class CacheConfigScheduler {

    @Autowired
    private AccessControlRepository accessControlRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PermissionsRepository permissionsRepository;

    @Autowired
    private CachingService cachingService;

//    @Scheduled(fixedDelay = 3000)
    public void syncAccessConfigCache() {

        try {
            Map<String, JPAResponseDTO.AccessConfigCache> accessControlMap = new HashMap<>();
            for (JPAResponseDTO.AccessConfigCache accessControl : accessControlRepository.findByRoleAndPermission()) {
                if (PermissionEnums.isPermissionExists(accessControl.getPermissionName())) {
                    String key = accessControl.getPermissionName() + AccessControlConstants.UNDERSCORE_SEPARATOR + accessControl.getRoleName();
                    accessControlMap.put(key, accessControl);
                }
            }
            Map<String, JPAResponseDTO.AccessConfigCache> accessControlMap2 = cachingService.get(CacheKey.ACCESS_CONTROL_CONFIG_CACHE.name(), new TypeReference<>() {
            });
            if (!accessControlMap.equals(accessControlMap2)) {
                cachingService.put(CacheKey.ACCESS_CONTROL_CONFIG_CACHE.name(), accessControlMap);
                log.info("Access Config cache updated for older config map {}", accessControlMap2);
            } else {
                log.info("Access Config cache is up-to-date.");
            }
        } catch (Exception ex) {
            log.error("Exception syncing access config cache {}", ex.getMessage());
        }
    }

//    @Scheduled(fixedDelay = 3000)
    public void syncPermissionsConfigCache() {

        try {
            Map<String, JPAResponseDTO.PermissionsJPADTO> permissionsMap = new HashMap<>();
            for (JPAResponseDTO.PermissionsJPADTO permissions : permissionsRepository.findAllPermissions()) {
                if (PermissionEnums.isPermissionExists(permissions.getPermissionName())) {
                    permissionsMap.put(permissions.getPermissionName(), permissions);
                }
            }
            Map<String, JPAResponseDTO.PermissionsJPADTO> permissionsMap2 = cachingService.get(CacheKey.PERMISSIONS_CONFIG_CACHE.name(), new TypeReference<>() {
            });
            if (!permissionsMap.equals(permissionsMap2)) {
                cachingService.put(CacheKey.PERMISSIONS_CONFIG_CACHE.name(), permissionsMap);
                log.info("Permissions cache updated for older config map {}", permissionsMap2);
            } else {
                log.info("Permissions cache is up-to-date.");
            }
        } catch (Exception ex) {
            log.error("Exception syncing permissions config cache {}", ex.getMessage());
        }
    }
//    @Scheduled(fixedDelay = 3000)
//    public void syncAccessConfigCache(){
//        try {
//            Map<String, PermissionsJPADTO> permissionsMap = new HashMap<>();
//            for(PermissionsJPADTO permissions : permissionsRepository.findAllPermissions()) {
//                if (PermissionEnums.isPermissionExists(permissions.getPermissionName())) {
//                    permissionsMap.put(permissions.getPermissionName(), permissions);
//                }
//            }
//            Map<String, PermissionsJPADTO> permissionsMap2 = cachingService.get("permissionsCache", new TypeReference<>() {});
//            if (!permissionsMap.equals(permissionsMap2)) {
//                cachingService.put("permissionsCache", permissionsMap);
//                log.info("Roles cache updated for older config map {}", permissionsMap2);
//            }
//            else {
//                log.info("Roles cache is up-to-date.");
//            }
//        }
//        catch(Exception ex){
//            log.error("Exception syncing roles config cache {}", ex.getMessage());
//        }
//    }

}
