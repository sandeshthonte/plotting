package com.arundhati.backend.plotting.service;

import com.arundhati.backend.plotting.common.exception.PlottingApiException;
import com.arundhati.backend.plotting.config.CachingService;
import com.arundhati.backend.plotting.dtos.RolesDTO;
import com.arundhati.backend.plotting.enums.ResponseCode;
import com.arundhati.backend.plotting.models.Roles;
import com.arundhati.backend.plotting.repository.RolesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private CachingService cachingService;

    public void addRoles(RolesDTO rolesDTO){
        Roles roles = getRolesByName(rolesDTO.getRoleName());
        if(roles != null){
            log.info("Role already present with name " + rolesDTO.getRoleName());
            throw new PlottingApiException(ResponseCode.PX_FAILURE_013);
        }
        roles = new Roles(rolesDTO.getRoleName());
        rolesRepository.save(roles);
    }

    public void disableRoles(RolesDTO rolesDTO){
        Roles roles = getRolesByName(rolesDTO.getRoleName());
        if(roles == null){
            log.info("Role absent with name " + rolesDTO.getRoleName());
            return;
        }
        roles.setIsActive(1 - roles.getIsActive());
        rolesRepository.save(roles);
    }

    public Roles getRoles(Long id){
        return rolesRepository.findById(id).orElseGet(() -> {
            log.info("Roles with id {} not found", id);
            return null;
        });
    }

    public Roles getRolesByName(String roleName){
        Roles roles = cachingService.get(roleName, Roles.class);
        if(roles == null){
            roles = rolesRepository.findByRoleName(roleName);
            cachingService.put(roles.getRoleName(), roles);
            if(roles == null){
                log.info("Roles with name {} not found", roleName);
            }
        }

        return roles;
    }

    public List<Roles> getAllRoles(){
        return rolesRepository.findAll();
    }
}
