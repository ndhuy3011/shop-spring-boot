package com.ndhuy.auth.user.domain.valueobject;

public record Email(String value) {
    public static final int MAX_LENGTH = 50;
    public static final int MIN_LENGTH = 3;
    public static final String PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    public static final String PATTERN_MESSAGE = "Email must be valid";
    public static final String LENGTH_MESSAGE = "Email must be between " + MIN_LENGTH + " and " + MAX_LENGTH
            + " characters";
    
}
