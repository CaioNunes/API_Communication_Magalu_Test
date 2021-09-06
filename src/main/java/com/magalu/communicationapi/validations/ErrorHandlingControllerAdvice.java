package com.magalu.communicationapi.validations;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorHandlingControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ValidationErrorResponse validationErrorReponse = getValidationErrorReponse(ex);
        return new ResponseEntity<>(validationErrorReponse, status);
    }

    ValidationErrorResponse getValidationErrorReponse(MethodArgumentNotValidException e){
        ValidationErrorResponse error = new ValidationErrorResponse();

        for(FieldError fieldError: e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        return error;
    }

}
