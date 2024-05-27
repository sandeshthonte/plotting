package com.arundhati.backend.plotting.controllers;

import com.arundhati.backend.plotting.dtos.*;
import com.arundhati.backend.plotting.enums.ResponseCode;
import com.arundhati.backend.plotting.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/userprofile/auth")
public class UserDetailsController {

   @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/adduser")
    ResponseEntity addUser(@RequestBody RequestDTOs.UserDetailsDTO userDetailsDTO){
        userDetailsService.addUser(userDetailsDTO);
        return ResponseDTO.SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001);
    }

    @RequestMapping("/toggleuser")
    ResponseEntity toggleUser(@RequestParam String userName){
        userDetailsService.toggleUserApi(userName);
        return ResponseDTO.SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001);
    }

    @GetMapping("/getuserdetails")
    ResponseEntity getUserDetails(@RequestParam("userName") String userName){
        return ResponseDTO.SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001, userDetailsService.findByUserNameActive(userName));
    }

    @GetMapping("/getuserdetailsid")
    ResponseEntity getUserDetailsById(@RequestParam("userId") Long userId){
        return ResponseDTO.SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001, userDetailsService.findById(userId));
    }
}
