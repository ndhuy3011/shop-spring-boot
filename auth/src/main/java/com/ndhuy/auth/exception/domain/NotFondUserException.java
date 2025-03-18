package com.ndhuy.auth.exception.domain;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class NotFondUserException extends UsernameNotFoundException {
    private  static final String MESSAGE = " User is null";

    public NotFondUserException() {
        super(MESSAGE);
    }

    public NotFondUserException( Throwable cause) {
        super(MESSAGE, cause);
    }
}