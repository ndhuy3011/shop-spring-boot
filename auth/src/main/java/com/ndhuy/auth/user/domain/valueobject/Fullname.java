package com.ndhuy.auth.user.domain.valueobject;

public record Fullname(String value) {
    public static final int MAX_LENGTH = 50;
    public static final int MIN_LENGTH = 3;
    public static final String PATTERN = "^[a-zA-Z ]+$";
    public static final String PATTERN_MESSAGE = "Fullname must contain only letters";
    public static final String LENGTH_MESSAGE = "Fullname must be between " + MIN_LENGTH + " and " + MAX_LENGTH
            + " characters";
}
