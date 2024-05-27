package com.arundhati.backend.plotting.common.exception;

import com.arundhati.backend.plotting.dtos.ResponseDTO;
import com.arundhati.backend.plotting.enums.ResponseCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PlottingExceptionHandler {

    @ExceptionHandler(PlottingApiException.class)
    public ResponseEntity handleBadRequestException(PlottingApiException ex) {
        return ResponseDTO.FailureResponseDTO.getFailureResponseDTO(ResponseCode.fromMessage(ex.getMessage()), ex.getMessage());
    }
}
