package com.arundhati.backend.plotting.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnums implements GrantedAuthority {
    DEVELOPER,
    SUPER_ADMIN,
    OWNER;

    @Override
    public String getAuthority() {
        return name();
    }
}
