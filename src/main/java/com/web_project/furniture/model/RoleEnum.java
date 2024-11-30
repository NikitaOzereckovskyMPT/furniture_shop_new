package com.web_project.furniture.model;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
    ADMIN, MANAGER, CUSTOMER;

    @Override
    public String getAuthority() {
        return name();
    }
}