package com.ndhuy.app.exception.application.runtime;

public class NotChangeRuntimeException extends RuntimeException {
    private final String messageKey;
    private final Object[] args;

    public NotChangeRuntimeException(String messageKey, Object... args) {
        this.messageKey = messageKey;
        this.args = args;
    }

    public NotChangeRuntimeException(String messageKey) {
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