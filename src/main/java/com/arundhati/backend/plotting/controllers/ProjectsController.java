package com.arundhati.backend.plotting.controllers;

import com.arundhati.backend.plotting.dtos.*;
import com.arundhati.backend.plotting.enums.ResponseCode;
import com.arundhati.backend.plotting.service.ProjectDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/plotting/projects")
public class ProjectsController {

    @Autowired
    private ProjectDetailsService projectDetailsService;

    @PostMapping("/addproject")
    ResponseEntity addProject(@RequestBody RequestDTOs.ProjectDetailsDTO projectDetailsDTO){
        projectDetailsService.addProject(projectDetailsDTO);
        return ResponseDTO.SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001);
    }

    @RequestMapping("/toggleproject")
    ResponseEntity toggleProject(@RequestParam("projectIdentifier") String projectIdentifier){
        projectDetailsService.toggleProject(projectIdentifier);
        return ResponseDTO.SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001);
    }

    @GetMapping("/getproject")
    ResponseEntity getProjectBySurveyNo(@RequestParam("projectIdentifier") String projectIdentifier){
        return ResponseDTO.SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001, projectDetailsService.findByProjectIdentifierAndIsActive(projectIdentifier));
    }

}
