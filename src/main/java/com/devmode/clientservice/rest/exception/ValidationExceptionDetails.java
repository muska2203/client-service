package com.devmode.clientservice.rest.exception;


import lombok.Getter;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ValidationExceptionDetails extends ExceptionDetails {

    private final Map<String, String> violations;


    public ValidationExceptionDetails(ConstraintViolationException exception) {
        super(ConstraintViolationException.class.getName(), exception.getMessage());
        violations = toMap(exception.getConstraintViolations());
    }

    private static Map<String, String> toMap(Set<ConstraintViolation<?>> constraintViolations) {
        return constraintViolations.stream()
                .collect(
                        Collectors.toMap(
                                violation -> violation.getPropertyPath().toString(),
                                ConstraintViolation::getMessage,
                                (a, b) -> String.join(",", a, b)
                        )
                );
    }
}
