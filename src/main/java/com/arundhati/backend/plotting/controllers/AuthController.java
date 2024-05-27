package com.arundhati.backend.plotting.controllers;

import com.arundhati.backend.plotting.dtos.*;
import com.arundhati.backend.plotting.enums.ResponseCode;
import com.arundhati.backend.plotting.service.configuration.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/internal/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    @PostMapping(path = "/token", consumes = APPLICATION_JSON_VALUE)
    public String getToken(@RequestBody Map<String, Object> claims) {
        return jwtService.generateJWT(claims);
    }

    @PostMapping("/signin")
    ResponseEntity addUser(@RequestBody RequestDTOs.UserDetailsDTO userDetailsDTO){
//        userDetailsService.addUser(userDetailsDTO);
        return ResponseDTO.SuccessCodeResponseDTO.getSuccessResponseDTO(ResponseCode.PX_SUCCESS_001);
    }

}