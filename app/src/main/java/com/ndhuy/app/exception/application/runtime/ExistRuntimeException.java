package com.ndhuy.app.exception.application.runtime;

public class ExistRuntimeException extends RuntimeException {
    private final String messageKey;
    private final Object[] args;

    public ExistRuntimeException(String messageKey, Object... args) {
        this.messageKey = messageKey;
        this.args = args;
    }

    public ExistRuntimeException(String messageKey) {
        this.messageKey = messageKey;
        this.args = null;
    }

    public String getMessageKey() {
        return messageKey;
    }
    public Object[] getArgs() {
        return args;
    }
}