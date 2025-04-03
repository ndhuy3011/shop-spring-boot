package com.ndhuy.app.exception.domain;

import java.util.List;
import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    String err;
    Locale locale;
    List<String> errorMessages;

    public ErrorMessage(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

}
