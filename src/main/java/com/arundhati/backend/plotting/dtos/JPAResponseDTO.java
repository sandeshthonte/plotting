package com.arundhati.backend.plotting.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JPAResponseDTO {

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class AccessConfigCache {
    public String roleName;
    private String permissionName;
    private String pathUri;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class PermissionsJPADTO {

    public String permissionName;
    public String description;
    public String pathUri;
  }
}
