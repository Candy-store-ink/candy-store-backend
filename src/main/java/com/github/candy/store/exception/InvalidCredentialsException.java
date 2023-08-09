package com.github.candy.store.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException  extends RuntimeException {

    private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

    private String message = "Invalid credentials";

    public InvalidCredentialsException() {
        super("Invalid credentials");
    }

    public InvalidCredentialsException(String message) {
        super(message);
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
