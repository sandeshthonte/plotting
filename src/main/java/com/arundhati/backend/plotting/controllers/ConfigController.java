package com.arundhati.backend.plotting.controllers;

import com.arundhati.backend.plotting.dtos.*;
import com.arundhati.backend.plotting.enums.ResponseCode;
import com.arundhati.backend.plotting.service.AccessControlService;
import com.arundhati.backend.plotting.service.PermissionsService;
import com.arundhati.backend.plotting.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/internal/config")
public class ConfigController {

    @Autowired
    private RolesService rolesService;

    @Autowired
    private PermissionsService permissionsService;

    @Autowired
    private AccessControlService accessControlService;

    @PostMapping("/addrole")
    ResponseEntity addRole(@RequestBody RolesDTO rolesDTO){
        rolesService.addRoles(rolesDTO);
        return ResponseDTO.SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001);
    }

    @RequestMapping("/togglerole")
    ResponseEntity removeRole(@RequestBody RolesDTO rolesDTO){
        rolesService.disableRoles(rolesDTO);
        return ResponseDTO.SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001);
    }

    @PostMapping("/addpermission")
    ResponseEntity addPermission(@RequestBody RequestDTOs.PermissionsDTO permissionsDTO){
        permissionsService.addPermissions(permissionsDTO);
        return ResponseDTO.SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001);
    }

    @RequestMapping("/togglepermission")
    ResponseEntity removePermission(@RequestBody RequestDTOs.PermissionsDTO permissionsDTO){
        permissionsService.togglePermissions(permissionsDTO);
        return ResponseDTO.SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001);
    }

    @GetMapping("/getallroles")
    ResponseEntity getAllRoles(){
        return ResponseDTO.SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001, rolesService.getAllRoles());
    }

    @GetMapping("/getrole")
    ResponseEntity getAllRoles(@RequestParam String roleName){
        return ResponseDTO.SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001, rolesService.getRolesByName(roleName));
    }

    @PostMapping("/addaccesscontrol")
    ResponseEntity addACC(@RequestBody RequestDTOs.AccessControlDTO accessControlDTO){
        accessControlService.addAccessControlConfig(accessControlDTO);
        return ResponseDTO.SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001);
    }

}
