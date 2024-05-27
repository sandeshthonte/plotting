package com.arundhati.backend.plotting.common.exception;

import com.arundhati.backend.plotting.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class PlottingApiException extends RuntimeException {
    private final String code;
    private final String message;
    private final String displayMessage;

    public PlottingApiException(ResponseCode responseCode){
        super(String.format("%s:%s %s", responseCode.getCode(), responseCode.getDisplayMessage(), responseCode.getMessage()));
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.displayMessage = responseCode.getDisplayMessage();
    }

    public ResponseCode getResponseCode(PlottingApiException plottingApiException){
        return ResponseCode.fromMessage(plottingApiException.getMessage());
    }

}
