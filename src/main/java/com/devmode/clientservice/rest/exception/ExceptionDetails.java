package com.devmode.clientservice.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionDetails {

    private final String type;

    private final String message;
}
