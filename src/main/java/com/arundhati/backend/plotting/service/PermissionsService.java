package com.arundhati.backend.plotting.service;

import com.arundhati.backend.plotting.common.utils.ConvertorUtils;
import com.arundhati.backend.plotting.dtos.RequestDTOs;
import com.arundhati.backend.plotting.models.Permissions;
import com.arundhati.backend.plotting.repository.PermissionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionsService {

    @Autowired
    private PermissionsRepository permissionsRepository;

    public void addPermissions(RequestDTOs.PermissionsDTO permissionsDTO){
        Permissions permissions = permissionsRepository.findByPermissionName(permissionsDTO.getPermissionName());
        if(permissions != null){
            return;
        }
        permissions = ConvertorUtils.convertToClass(permissionsDTO, Permissions.class);
        if(permissions != null){
            permissionsRepository.save(permissions);
        }
    }

    public void togglePermissions(RequestDTOs.PermissionsDTO permissionsDTO){
        Permissions permissions = permissionsRepository.findByPermissionName(permissionsDTO.getPermissionName());
        if(permissions == null){
            return;
        }
        permissions.setIsActive(1 - permissions.getIsActive());
        permissionsRepository.save(permissions);
    }

    public Permissions getPermissions(Long id){
        return permissionsRepository.findById(id).orElse(null);
    }

    public Permissions getPermissionsByName(String permissionName){
        return permissionsRepository.findByPermissionName(permissionName);
    }

}
