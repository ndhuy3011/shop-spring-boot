package com.ndhuy.auth.exception.domain;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class PasswordRuntimeException extends UsernameNotFoundException {
   private  static final String MESSAGE = " Password is wrong";

    public PasswordRuntimeException() {
        super(MESSAGE);
    }

    public PasswordRuntimeException( Throwable cause) {
        super(MESSAGE, cause);
    }
}
