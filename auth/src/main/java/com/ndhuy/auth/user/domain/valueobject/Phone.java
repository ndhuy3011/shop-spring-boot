package com.ndhuy.auth.user.domain.valueobject;

public record Phone(String value) {
    public static final String REGEX = "^(\\+84|0)\\d{9,10}$";
    public static final String REGEX_MESSAGE = "Phone is invalid";
    public static final int MAX_LENGTH = 11;

    public static final void validate(String value) {
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Phone must be less than " + MAX_LENGTH + " characters");
        }
    }

    public static Phone of(String value) {
        validate(value);
        return new Phone(value);
    }

    public Phone {
        validate(value);
    }
}
