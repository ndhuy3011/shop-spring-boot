package com.ndhuy.auth.user.domain.valueobject;

public record Address(String value) {
    public static final int MAX_LENGTH = 255;
    public static final int MIN_LENGTH = 3;
    public static final String LENGTH_MESSAGE = "Address must be between " + MIN_LENGTH + " and " + MAX_LENGTH
            + " characters";
    public static final String NULL_MESSAGE = "Address must not be null or empty";
    
    /** 
     * @param value
     */
    public static final void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(LENGTH_MESSAGE);
        }
    }

    public static final Address of(String value) {
        validate(value);
        return new Address(value);
    }
}
