package com.ndhuy.auth.user.domain.valueobject;

public record RoleKey(String value) {
    public RoleKey {
        if (value == null ) {
            throw new IllegalArgumentException("role key must not be null or empty");
        }
    }
    public static RoleKey of(String value) {
        return new RoleKey(value);
    }
}
