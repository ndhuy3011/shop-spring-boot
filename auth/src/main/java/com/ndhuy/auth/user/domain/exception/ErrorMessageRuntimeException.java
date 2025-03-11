package com.ndhuy.auth.user.domain.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class ErrorMessageRuntimeException extends RuntimeException{
    private final String messageKey;
    private final String errorCode;
    private final Object[] args;
    private final MessageSource messageSource;
    private final Locale locale;

    public ErrorMessageRuntimeException(String messageKey, String errorCode, Object[] args, MessageSource messageSource, Locale locale) {
        super(messageKey);
        this.messageKey = messageKey;
        this.errorCode = errorCode;
        this.args = args;
        this.messageSource = messageSource;
        this.locale = locale;
    }

    @Override
    public String getMessage() {
        return messageSource.getMessage(messageKey, args, locale);
    }

    public String getErrorCode() {
        return errorCode;
    }
}
