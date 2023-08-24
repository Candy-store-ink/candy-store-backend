package com.github.candy.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        return makeErrorResponse(ex.getHttpStatus(), ex.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return makeErrorResponse(ex.getHttpStatus(), ex.getMessage());
    }

    private static ResponseEntity<ErrorResponse> makeErrorResponse(HttpStatus ex, String message) {
        return ResponseEntity
                .status(ex)
                .body(new ErrorResponse(ex, message));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return makeErrorResponse(ex.getHttpStatus(), ex.getMessage());
    }
}
