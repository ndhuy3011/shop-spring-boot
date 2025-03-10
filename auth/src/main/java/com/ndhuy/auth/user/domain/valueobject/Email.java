package com.ndhuy.auth.user.domain.valueobject;

public record Email(String value) {
    public static final int MAX_LENGTH = 50;
    public static final int MIN_LENGTH = 3;
    public static final String PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    public static final String PATTERN_MESSAGE = "Email must be valid";
    public static final String LENGTH_MESSAGE = "Email must be between " + MIN_LENGTH + " and " + MAX_LENGTH
            + " characters";
    public static final void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email must not be null or empty");
        }
        if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(LENGTH_MESSAGE);
        }
        if (!value.matches(PATTERN)) {
            throw new IllegalArgumentException(PATTERN_MESSAGE);
        }
    }
    public static Email of(String value) {
        validate(value);
        return new Email(value);
    }
}
