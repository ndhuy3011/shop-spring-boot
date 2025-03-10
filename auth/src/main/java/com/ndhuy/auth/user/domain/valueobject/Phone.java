package com.ndhuy.auth.user.domain.valueobject;

public record Phone(String value) {
    public static final String REGEX = "^(\\+84|0)\\d{9,10}$";
    public static final String REGEX_MESSAGE = "Phone is invalid";
    public static final int MAX_LENGTH = 11;
    public Phone {

        if (!value.matches(REGEX)) {
            throw new IllegalArgumentException(REGEX_MESSAGE);
        }
    }
}
