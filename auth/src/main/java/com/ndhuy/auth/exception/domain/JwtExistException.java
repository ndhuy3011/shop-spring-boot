package com.ndhuy.auth.exception.domain;

public class JwtExistException extends RuntimeException {
    private  static final String MESSAGE = " Jwt Exist";

    public JwtExistException() {
        super(MESSAGE);
    }

    public JwtExistException( Throwable cause) {
        super(MESSAGE, cause);
    }
}
