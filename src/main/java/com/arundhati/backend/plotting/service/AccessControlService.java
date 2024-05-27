package com.arundhati.backend.plotting.service;

import com.arundhati.backend.plotting.dtos.RequestDTOs;
import com.arundhati.backend.plotting.models.AccessControl;
import com.arundhati.backend.plotting.models.Permissions;
import com.arundhati.backend.plotting.models.Roles;
import com.arundhati.backend.plotting.repository.AccessControlRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessControlService implements IAccessControlService{

    @Autowired
    private AccessControlRepository accessControlRepository;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private PermissionsService permissionsService;

    @Autowired
    ObjectMapper objectMapper;

    public void addAccessControlConfig(RequestDTOs.AccessControlDTO accessControlDTO){
        Roles roles = rolesService.getRolesByName(accessControlDTO.getRoleName());
        Permissions permissions = permissionsService.getPermissionsByName(accessControlDTO.getPermissionName());
        if(roles == null || permissions == null){
            return;
        }

        AccessControl accessControl = accessControlRepository.findByPermissionNameAndRoleName(accessControlDTO.getPermissionName(), accessControlDTO.getRoleName());
        if(accessControl != null){
            return;
        }
        accessControl = new AccessControl(accessControlDTO.getPermissionName(), accessControlDTO.getRoleName()
        );
        accessControlRepository.save(accessControl);
    }

    public AccessControl findAccessByParameters(RequestDTOs.AccessControlDTO accessControlDTO){
        return accessControlRepository.findByPermissionNameAndRoleName(
                accessControlDTO.getPermissionName(), accessControlDTO.getRoleName());
    }

    public Boolean hasAccess(RequestDTOs.AccessControlDTO accessControlDTO){
        AccessControl accessControl = findAccessByParameters(accessControlDTO);
        if(accessControl == null){
            return false;
        }
        return true;
    }

}
