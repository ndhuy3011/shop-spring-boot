package com.ndhuy.auth.user.domain.valueobject;

public record Username(String value) {
    public static final int MAX_LENGTH = 50;
    public static final int MIN_LENGTH = 3;
    public static final String USERNAME_PATTERN = "^[a-zA-Z0-9]+$";
    public static final String USERNAME_PATTERN_MESSAGE = "Username must contain only letters and numbers";
    public static final String USERNAME_LENGTH_MESSAGE = "Username must be between " + MIN_LENGTH + " and " + MAX_LENGTH
            + " characters";
    public static final String USERNAME_NULL_MESSAGE = "Username must not be null or empty";

    public Username {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(USERNAME_NULL_MESSAGE);
        }
        if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(USERNAME_LENGTH_MESSAGE);
        }
        if (!value.matches(USERNAME_PATTERN)) {
            throw new IllegalArgumentException(USERNAME_PATTERN_MESSAGE);
        }
    }

    public static Username of(String value) {
        return new Username(value);
    }
}
