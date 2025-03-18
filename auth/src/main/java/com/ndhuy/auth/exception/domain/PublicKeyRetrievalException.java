package com.ndhuy.auth.exception.domain;

public class PublicKeyRetrievalException extends RuntimeException {
    public PublicKeyRetrievalException(String message) {
        super(message);
    }

    public PublicKeyRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}