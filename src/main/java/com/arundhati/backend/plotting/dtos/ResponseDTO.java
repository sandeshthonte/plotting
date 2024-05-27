package com.arundhati.backend.plotting.dtos;

import com.arundhati.backend.plotting.enums.ResponseCode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * shweta shaw 10/5/19
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDTO {

  private MetaDTO meta;
  private Object data;

  public static class FailureResponseDTO {

      public static ResponseEntity getFailureResponseDTO(ResponseCode responseCode, Object object){
          ResponseDTO responseDTO = new ResponseDTO();
          responseDTO.setMeta(getMetaDTO(responseCode));
          responseDTO.setData(object);
          return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
      }
  }

  public static class SuccessCodeResponseDTO {
      public static ResponseEntity getSuccessResponseDTO(ResponseCode responseCode){
          ResponseDTO responseDTO = new ResponseDTO();
          responseDTO.setMeta(getMetaDTO(responseCode));
          return new ResponseEntity(responseDTO, HttpStatus.OK);
      }

      public static ResponseEntity getSuccessResponseDTO(ResponseCode responseCode, Object object){
          ResponseDTO responseDTO = new ResponseDTO();
          responseDTO.setMeta(getMetaDTO(responseCode));
          responseDTO.setData(object);
          return new ResponseEntity(responseDTO, HttpStatus.OK);
      }

      public static ResponseEntity getFailureResponseDTO(ResponseCode responseCode, Object object){
          ResponseDTO responseDTO = new ResponseDTO();
          responseDTO.setMeta(getMetaDTO(responseCode));
          responseDTO.setData(object);
          return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
      }
  }

    public static ResponseDTO.MetaDTO getMetaDTO(ResponseCode responseCode) {
//        return ConvertorUtils.convertToClass(responseCode, MetaDTO.class);
        return new ResponseDTO.MetaDTO(responseCode);
    }

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  static
  class MetaDTO {
    private String code;
    private String message;
  //  private String responseId;
  //  private String requestId;
    private String displayMessage;

    public MetaDTO(ResponseCode responseCode) {
      this.code = responseCode.getCode();
      this.message = responseCode.getMessage();
      this.displayMessage = responseCode.getDisplayMessage();
    }
  }
}

