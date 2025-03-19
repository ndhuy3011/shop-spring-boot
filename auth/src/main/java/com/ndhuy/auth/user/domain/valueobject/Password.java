package com.ndhuy.auth.user.domain.valueobject;

public record Password(String value) {
    public static final int MIN_LENGTH = 8;
    public static final int MAX_LENGTH = 100;
    public static final String PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{" + MIN_LENGTH + ","
            + MAX_LENGTH + "}$";
    public static final String PATTERN_MESSAGE = "Password must contain at least one digit, one lowercase letter, one uppercase letter, no whitespace and at least 8 characters";
    public static final String LENGTH_MESSAGE = "Password must be between " + MIN_LENGTH + " and " + MAX_LENGTH
            + " characters";
    public static final String NULL_MESSAGE = "Password must not be null or empty";

    public Password {
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

    public static Password of(String password) {
        return new Password(password);
    }

}
