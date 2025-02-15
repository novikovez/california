package org.california.enums;

import java.util.Arrays;

public enum UserRole {
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_GUEST;

    public static boolean exists(String role) {
        return Arrays.stream(UserRole.values()).anyMatch(r -> r.name().equals(role));
    }
}
