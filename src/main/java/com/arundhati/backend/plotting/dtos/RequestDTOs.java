package com.arundhati.backend.plotting.dtos;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class RequestDTOs {
    @Getter
    public static class UserDetailsDTO {

        private String username;
        private String email;
        private String mobile;
        private Long roleId;
        private String password;
    }

    @Getter
    public static class PlotDetailsDTO {

        private String plotName;
        private String description;
        private String plotIdentifier;
        private Long plotNo;
        private Long projectId;
        private Double squareFeet;
        private Double squareMeters;
        private String layoutName;
    }

    @Getter
    public static class ProjectDetailsDTO {

        private String projectName;
        private String description;
        private String projectIdentifier;
        private Long surveyNo;
        private String layoutName;

        @Embedded
        private Embeddables.Address address;
    }

    @Getter
    public static class AllocationDetailsDTO {
        private String userName;
        private Long projectId;
        private Long plotId;
        private String ownerType;
    }

    @Getter
    public static class AccessControlDTO {
        private String permissionName;
        private String roleName;

        public AccessControlDTO(String permissionName, String roleName) {
            this.permissionName = permissionName;
            this.roleName = roleName;
        }
    }

    @Getter
    public static class PermissionsDTO {

        private String permissionName;
        private String description;
        private String pathUri;
    }
}

