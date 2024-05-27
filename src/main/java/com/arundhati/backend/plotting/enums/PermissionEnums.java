package com.arundhati.backend.plotting.enums;

public enum PermissionEnums {
    READ_PROJECTS, ADD_ALLOCATION;

    public static boolean isPermissionExists(String permission) {
        for (PermissionEnums p : PermissionEnums.values()) {
            if (p.name().equals(permission)) {
                return true;
            }
        }
        return false;
    }
}
