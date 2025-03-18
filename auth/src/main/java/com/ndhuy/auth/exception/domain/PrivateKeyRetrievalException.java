package com.ndhuy.auth.exception.domain;

public class PrivateKeyRetrievalException extends RuntimeException {
    public PrivateKeyRetrievalException(String message) {
        super(message);
    }

    public PrivateKeyRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}