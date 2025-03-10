package com.ndhuy.auth.user.domain.valueobject;

public record Address(String value) {
    public static final int MAX_LENGTH = 255;
    public static final int MIN_LENGTH = 3;
    public static final String LENGTH_MESSAGE = "Address must be between " + MIN_LENGTH + " and " + MAX_LENGTH
            + " characters";
    public static final String NULL_MESSAGE = "Address must not be null or empty";
    
}
