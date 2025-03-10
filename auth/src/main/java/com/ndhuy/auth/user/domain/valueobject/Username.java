package com.ndhuy.auth.user.domain.valueobject;

public record Username(String value) {
    public static final int MAX_LENGTH = 50;
    public static final int MIN_LENGTH = 3;
    public static final String PATTERN = "^[a-zA-Z0-9]+$";
    public static final String PATTERN_MESSAGE = "Username must contain only letters and numbers";
    public static final String LENGTH_MESSAGE = "Username must be between " + MIN_LENGTH + " and " + MAX_LENGTH
            + " characters";
    public static final String NULL_MESSAGE = "Username must not be null or empty";

    public static final void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(LENGTH_MESSAGE);
        }
        if (!value.matches(PATTERN)) {
            throw new IllegalArgumentException(PATTERN_MESSAGE);
        }
    }

    public Username {
        validate(value);
    }

    public static Username of(String value) {
        return new Username(value);
    }
}
