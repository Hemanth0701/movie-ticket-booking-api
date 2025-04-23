package com.tenjiku.mtb.service;

import com.tenjiku.mtb.entity.enums.Role;

public class RoleService {

    public static Role fromString(String role) {
        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException("Role cannot be null or empty.");
        }
        try {
            return Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
    }

}
