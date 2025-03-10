package com.ndhuy.auth.user.domain.valueobject;

public record Username(String value) {
    public Username {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Username must not be null or empty");
        }
    }

    public static Username of(String value) {
        return new Username(value);
    }
}
