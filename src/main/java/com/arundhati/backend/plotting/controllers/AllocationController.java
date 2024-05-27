package com.arundhati.backend.plotting.controllers;

import com.arundhati.backend.plotting.dtos.RequestDTOs;
import com.arundhati.backend.plotting.dtos.ResponseDTO;
import com.arundhati.backend.plotting.enums.ResponseCode;
import com.arundhati.backend.plotting.service.AllocationDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/plotting/allocation")
public class AllocationController {

    @Autowired
    private AllocationDetailsService allocationDetailsService;

    @PostMapping("/addallocation")
    ResponseEntity addAllocation(@RequestBody RequestDTOs.AllocationDetailsDTO allocationDetailsDTO){
        allocationDetailsService.addProjectAllocation(allocationDetailsDTO);
        return ResponseDTO.SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001);
    }

//    @RequestMapping("/toggleproject")
//    ResponseEntity toggleProject(@RequestBody ProjectDetailsDTO.ProjectSurveyNoDTO projectSurveyNoDTO){
//        projectDetailsService.toggleProject(projectSurveyNoDTO);
//        return SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001);
//    }

//    @PostMapping("/addpermission")
//    ResponseEntity addPermission(@RequestBody PermissionsDTO permissionsDTO){
//        permissionsService.addPermissions(permissionsDTO);
//        return SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001);
//    }
//
//    @RequestMapping("/togglepermission")
//    ResponseEntity removePermission(@RequestBody PermissionsDTO permissionsDTO){
//        permissionsService.togglePermissions(permissionsDTO);
//        return SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001);
//    }
//
//    @GetMapping("/getproject")
//    ResponseEntity getProjectBySurveyNo(@RequestBody ProjectDetailsDTO.ProjectSurveyNoDTO projectSurveyNoDTO){
//        return SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001, projectDetailsService.getProjectBySurveyNo(projectSurveyNoDTO.getSurveyNo()));
//    }

//    @GetMapping("/getallaccesscontrols")
//    ResponseEntity getAllAccessControls(){
//        return SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001,accessControlService.getAllACC());
//    }

}
