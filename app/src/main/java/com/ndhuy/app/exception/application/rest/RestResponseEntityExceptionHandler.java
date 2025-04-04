package com.ndhuy.app.exception.application.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.ndhuy.app.exception.application.runtime.ErrorMessageRuntimeException;
import com.ndhuy.app.exception.application.runtime.ExistRuntimeException;
import com.ndhuy.app.exception.application.runtime.ForbiddenRuntimeException;
import com.ndhuy.app.exception.application.runtime.NotChangeRuntimeException;
import com.ndhuy.app.exception.application.runtime.NotFoundRuntimeException;
import com.ndhuy.app.exception.application.runtime.UnauthorizedRuntimeException;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {
    @Resource
    MessageSource messageSource;
    @Resource
    private HttpServletRequest request;

    @ExceptionHandler(ErrorMessageRuntimeException.class)
    public ResponseEntity<Object> handleErrorMessageRuntimeException(ErrorMessageRuntimeException ex,
            WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(NullPointerException ex) {
        Map<String, String> errorResponse = new HashMap<>();

        String translatedMessage = messageSource.getMessage(
                ex.getMessage(),
                null,
                "Resource not found",
                request.getLocale());

        errorResponse.put("error", translatedMessage);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();

            String translatedMessage = messageSource.getMessage(
                    errorMessage,
                    null,
                    errorMessage,
                    request.getLocale());
            errors.put(fieldName, translatedMessage);
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();

        String translatedMessage = messageSource.getMessage(
                "error.generic",
                null,
                "An unexpected error occurred",
                request.getLocale());
        errorResponse.put("error", translatedMessage);

        HttpStatus status = determineHttpStatus(ex);
        return new ResponseEntity<>(errorResponse, status);
    }

    private HttpStatus determineHttpStatus(Exception ex) {
        if (ex instanceof IllegalArgumentException) {
            return HttpStatus.BAD_REQUEST;
        } else if (ex instanceof UnsupportedOperationException) {
            return HttpStatus.BAD_GATEWAY;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @ExceptionHandler(NotFoundRuntimeException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(NotFoundRuntimeException ex) {
        Map<String, String> errorResponse = new HashMap<>();

        String translatedMessage = messageSource.getMessage(
                ex.getMessageKey(),
                ex.getArgs(),
                "Resource not found",
                request.getLocale());

        errorResponse.put("error", translatedMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedRuntimeException.class)
    public ResponseEntity<Map<String, String>> handleUnauthorizedException(UnauthorizedRuntimeException ex) {
        Map<String, String> errorResponse = new HashMap<>();

        String translatedMessage = messageSource.getMessage(
                ex.getMessageKey(),
                ex.getArgs(),
                "Unauthorized access",
                request.getLocale());

        errorResponse.put("error", translatedMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenRuntimeException.class)
    public ResponseEntity<Map<String, String>> handleForbiddendException(ForbiddenRuntimeException ex) {
        Map<String, String> errorResponse = new HashMap<>();

        String translatedMessage = messageSource.getMessage(
                ex.getMessageKey(),
                ex.getArgs(),
                "Forbidden access",
                request.getLocale());

        errorResponse.put("error", translatedMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ExistRuntimeException.class)
    public ResponseEntity<Map<String, String>> handleExistException(ExistRuntimeException ex) {
        Map<String, String> errorResponse = new HashMap<>();

        String translatedMessage = messageSource.getMessage(
                ex.getMessageKey(),
                ex.getArgs(),
                "Object already exists",
                request.getLocale());

        errorResponse.put("error", translatedMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotChangeRuntimeException.class)
    public ResponseEntity<Map<String, String>> handleNotChangeException(NotChangeRuntimeException ex) {
        Map<String, String> errorResponse = new HashMap<>();

        String translatedMessage = messageSource.getMessage(
                ex.getMessageKey(),
                ex.getArgs(),
                "Object already exists",
                request.getLocale());

        errorResponse.put("error", translatedMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
