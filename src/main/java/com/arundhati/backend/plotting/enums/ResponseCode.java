package com.arundhati.backend.plotting.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    PX_SUCCESS_001("200", "PX_SUCCESS_001", "Request Successful"),








//    -------------------Failure codes-------------------------------------------------------
    PX_FAILURE_001("500", "PX_FAILURE_001", "Internal Server Error"),
    PX_FAILURE_011("400", "PX_FAILURE_011", "Invalid Input Parameters"),
    PX_FAILURE_012("400", "PX_FAILURE_012", "Invalid Role Requested"),
    PX_FAILURE_013("400", "PX_FAILURE_013", "Data already present in db"),
    PX_FAILURE_014("400", "PX_FAILURE_014", "Invalid Role Requested"),
    PX_FAILURE_015("400", "PX_FAILURE_015", "Invalid Role Requested"),
    PX_FAILURE_016("400", "PX_FAILURE_016", "Invalid Role Requested"),
    PX_FAILURE_017("400", "PX_FAILURE_017", "Invalid Role Requested"),
    PX_FAILURE_018("400", "PX_FAILURE_018", "Invalid Role Requested"),
    PX_FAILURE_019("400", "PX_FAILURE_019", "Invalid Role Requested"),
    PX_FAILURE_020("400", "PX_FAILURE_020", "Invalid Role Requested"),
    PX_FAILURE_021("400", "PX_FAILURE_021", "User already exists"),
    PX_FAILURE_022("400", "PX_FAILURE_022", "User doesn't exist"),
    PX_FAILURE_023("400", "PX_FAILURE_023", "Project already exists"),
    PX_FAILURE_024("400", "PX_FAILURE_024", "Project doesn't exist"),
    PX_FAILURE_025("400", "PX_FAILURE_025", "Plot already exists"),
    PX_FAILURE_026("400", "PX_FAILURE_026", "Plot doesn't exist"),
    PX_FAILURE_027("400", "PX_FAILURE_027", "Invalid Role Requested"),
    PX_FAILURE_028("400", "PX_FAILURE_028", "Invalid Role Requested"),
    PX_FAILURE_029("400", "PX_FAILURE_029", "Invalid Role Requested"),
    PX_FAILURE_030("400", "PX_FAILURE_030", "Invalid Role Requested"),
    PX_FAILURE_031("400", "PX_FAILURE_031", "Invalid Role Requested"),
    PX_FAILURE_032("400", "PX_FAILURE_032", "Invalid Role Requested"),
    PX_FAILURE_033("400", "PX_FAILURE_033", "Invalid Role Requested"),


    ;

    private final String code;
    private final String message;
    private final String displayMessage;

    public static ResponseCode fromMessage(String message){
            for (ResponseCode p : ResponseCode.values()) {
                if (p.getMessage().startsWith(message)) {
                    return p;
                }
            }
            return ResponseCode.PX_FAILURE_001;
    }

}
