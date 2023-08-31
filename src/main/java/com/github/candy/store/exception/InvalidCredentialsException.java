package com.github.candy.store.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends BaseHttpException {

    public InvalidCredentialsException() {
        super("Invalid credentials");
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }

    public InvalidCredentialsException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    @Override
    protected HttpStatus defaultHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    protected String defaultMessage() {
        return "Invalid credentials";
    }

}
