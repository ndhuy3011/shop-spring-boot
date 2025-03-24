package com.ndhuy.auth.exception.domain;

public class JwtNotFoundException extends RuntimeException {
    private static final String MESSAGE = " Jwt not Exist";

    public JwtNotFoundException() {
        super(MESSAGE);
    }

    public JwtNotFoundException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
