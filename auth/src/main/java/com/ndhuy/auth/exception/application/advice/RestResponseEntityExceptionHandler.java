package com.ndhuy.auth.exception.application.advice;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.ndhuy.auth.exception.domain.ErrorMessage;
import com.ndhuy.auth.exception.domain.ErrorMessageRuntimeException;

import jakarta.annotation.Resource;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler{
    @Resource
    MessageSource messageSource;

    @ExceptionHandler(ErrorMessageRuntimeException.class)
    public ResponseEntity<Object> handleErrorMessageRuntimeException(ErrorMessageRuntimeException ex,
            WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleArgumentNotValidException(MethodArgumentNotValidException ex,
            Locale locale) {
        BindingResult result = ex.getBindingResult();
        List<String> errorMessages = result.getAllErrors()
                .stream()
                .map(err -> messageSource.getMessage(err, locale))
                .toList();
        return new ResponseEntity<>(new ErrorMessage(errorMessages), HttpStatus.BAD_REQUEST);
    }
    
}
