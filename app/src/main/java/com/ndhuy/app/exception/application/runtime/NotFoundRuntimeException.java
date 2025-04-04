package com.ndhuy.app.exception.application.runtime;

public class NotFoundRuntimeException extends RuntimeException {
    private final String messageKey;
    private final Object[] args;

    public NotFoundRuntimeException(String messageKey, Object... args) {
        this.messageKey = messageKey;
        this.args = args;
    }

    public NotFoundRuntimeException(String messageKey) {
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
