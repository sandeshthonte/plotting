package com.arundhati.backend.plotting.controllers;

import com.arundhati.backend.plotting.dtos.*;
import com.arundhati.backend.plotting.enums.ResponseCode;
import com.arundhati.backend.plotting.service.PlotDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/plotting/plots")
public class PlotsController {

    @Autowired
    private PlotDetailsService plotDetailsService;

    @PostMapping("/addplot")
    ResponseEntity addPlot(@RequestBody RequestDTOs.PlotDetailsDTO plotDetailsDTO){
        plotDetailsService.addPlot(plotDetailsDTO);
        return ResponseDTO.SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001);
    }

    @RequestMapping("/toggleplot")
    ResponseEntity removeRole(@RequestParam String identifier){
        plotDetailsService.togglePlot(identifier);
        return ResponseDTO.SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001);
    }

//    @PostMapping("/addpermission")
//    ResponseEntity addPermission(@RequestBody PermissionsDTO permissionsDTO){
//        permissionsService.addPermissions(permissionsDTO);
//        return SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001);
//    }

    @GetMapping("/getplot")
    ResponseEntity getPlots(@RequestParam String identifier){
        return ResponseDTO.SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001, plotDetailsService.findByPlotIdentifierAndIsActive(identifier));
    }
//
//    @GetMapping("/getallaccesscontrols")
//    ResponseEntity getAllAccessControls(){
//        return SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001,accessControlService.getAllACC());
//    }

}
